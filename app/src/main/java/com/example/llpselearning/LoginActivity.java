package com.example.llpselearning;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import org.json.*;

public class LoginActivity extends AppCompatActivity {

    EditText admiId,dob;
    Button Submit;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);

        admiId = (EditText)findViewById(R.id.admiId);
        dob = (EditText)findViewById(R.id.dob);
        Submit   = (Button)findViewById(R.id.Submit);

        Bundle extras = getIntent().getExtras();
        final String role = extras.getString("role");
        //Toast.makeText(getApplicationContext(),"Login Key :"+role,Toast.LENGTH_LONG).show();

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean adminIdExists = isNotEmpty(admiId);
                boolean dobExists = isNotEmpty(dob);

                if(adminIdExists == true && dobExists == true){
                    requestQueue = Volley.newRequestQueue(getApplicationContext());

                    String x = "https://hob1hvw5t6.execute-api.us-east-1.amazonaws.com/deployGetStudentRecord/resourcegetstudentrecord?Id=";
                    String url2 = x.concat(admiId.getText().toString()+"&dob="+dob.getText().toString()+"&Role="+role);
                    JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url2, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONObject body = response.getJSONObject("body");
                                if(body.has("Item")){

                                    JSONObject Item = body.getJSONObject("Item");
                                    String id,Dob,name;
                                    if(Item.has("StudentId")){
                                        id = Item.getString("StudentId");
                                        Dob = Item.getString("Dob");
                                        name = Item.getString("StudentName");
                                    }else{
                                        id = Item.getString("TeacherId");
                                        Dob = Item.getString("Dob");
                                        name = Item.getString("TeacherName");
                                    }

                                    boolean IdIsNull  = isEmpty(id);
                                    boolean dobIsNull = isEmpty(Dob);

                                    if(IdIsNull == true || dobIsNull == true){
                                        Toast toast51 = Toast.makeText(getApplicationContext(), "Sorry Record not Found!", Toast.LENGTH_LONG);
                                        toast51.show();
                                        admiId.setText("");
                                        dob.setText("");
                                    }else{
                                        Toast toast51 = Toast.makeText(getApplicationContext(), "AdmissionNo: "+id+" Name: "+name+" Dob: "+Dob, Toast.LENGTH_LONG);
                                        toast51.show();
                                        //Opening Student or Teachers page based on role
                                        if(Item.has("StudentId")){
                                            Intent j = new Intent(getApplicationContext(),StudentPageActivity.class);
                                            j.putExtra("Message","Welcome to Student Page");
                                            startActivity(j);
                                        }else{
                                            Intent j = new Intent(getApplicationContext(),StudentPageActivity.class);
                                            j.putExtra("Message","Welcome to Teachers Page");
                                            startActivity(j);
                                        }
                                    }
                                }else{
                                    Toast toast222 = Toast.makeText(getApplicationContext(), "Sorry Record not Found!", Toast.LENGTH_LONG);
                                    toast222.show();
                                    admiId.setText("");
                                    dob.setText("");
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Error.Response","Error Response");
                        }
                    });

                    requestQueue.add(getRequest);
                }else if(dobExists == false && adminIdExists == true){
                    Toast toast1 = Toast.makeText(getApplicationContext(),"Please Enter Dob",Toast. LENGTH_SHORT);
                    toast1.show();
                }else if(dobExists == true && adminIdExists == false){
                    Toast toast2 = Toast.makeText(getApplicationContext(),"Please Enter Admission Id ",Toast. LENGTH_SHORT);
                    toast2.show();
                }else if(dobExists == false && adminIdExists == false){
                    Toast toast = Toast.makeText(getApplicationContext(),"Please Enter Admission Id and Dob",Toast. LENGTH_SHORT);
                    toast.show();
                }

            }
        });
    }

    public boolean isNotEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return true;

        return false;
    }

    public boolean isEmpty(String x) {
        if (x.trim().length() > 0)
            return false;

        return true;
    }
}
