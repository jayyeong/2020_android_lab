package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private EditText _id,_pass,_name;
    private Button btn_signup;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        _id=findViewById(R.id._id);
        _pass=findViewById(R.id._pass);
        _name=findViewById(R.id._name);
        btn_signup = findViewById(R.id.btn_signup);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String userID = _id.getText().toString();
                String userPass = _pass.getText().toString();
                String userName = _name.getText().toString();
            }
        });
    }
}
