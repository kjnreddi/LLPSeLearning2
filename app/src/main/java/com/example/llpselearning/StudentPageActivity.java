package com.example.llpselearning;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StudentPageActivity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentpageactivity);

        Bundle extras = getIntent().getExtras();
        String Message = extras.getString("Message");

        tv = (TextView)findViewById(R.id.tv);
        tv.setText(Message);
    }
}
