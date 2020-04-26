package com.example.llpselearning;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.RequestQueue;


public class TeacherPageActivity extends AppCompatActivity {

    EditText admiId,dob;
    Button Submit;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacherpageactivity);
    }
}
