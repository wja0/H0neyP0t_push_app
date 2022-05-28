package com.example.h0neyp0t;

import com.google.firebase.messaging.FirebaseMessagingService;
import android.app.NotificationChannel;
import com.google.firebase.messaging.RemoteMessage;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;
import android.net.Uri;
import android.media.RingtoneManager;
import androidx.core.app.NotificationCompat;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

public class FirebaseFCM extends FirebaseMessagingService{
    private  String TAG = "FirebaseFCM";
    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.
        sendRegistrationToServer(token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        sendNotification(remoteMessage.getData().get("title"), remoteMessage.getData().get("body"));
    }

    private void sendNotification(String messageTitle, String messageBody) {
        Log.d(TAG, "Send message: " + messageTitle + " " + messageBody);

        Intent intent = new Intent(this, DetectAttack.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.default_notification_channel_id);
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.honeypot)
                        .setContentTitle(messageTitle)
                        .setContentText(messageBody)
                        .setAutoCancel(true)
//                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

    }

    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
        Log.d(TAG, "Token To server: " + token);
    }

}
