package com.example.h0neyp0t;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import android.net.Uri;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class DetectAttack extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    Button btn_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect);

        btn_url = findViewById(R.id.btn_url);

        btn_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.krcert.or.kr/data/guideView.do?bulletin_writing_sequence=36355&queryString=YnVsbGV0aW5fd3JpdGluZ19zZXF1ZW5jZT0zNTI5Ng=="));
                startActivity(urlintent);
            }
        });

    }

}
