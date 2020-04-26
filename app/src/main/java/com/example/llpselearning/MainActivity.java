package com.example.llpselearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button StudentLogin,TeacherLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StudentLogin = (Button)findViewById(R.id.StudentLogin);
        TeacherLogin = (Button)findViewById(R.id.TeacherLogin);

        StudentLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                i.putExtra("role","Student");
                startActivity(i);
            }
        });

        TeacherLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                i.putExtra("role","Teacher");
                startActivity(i);
            }
        });

    }
}
