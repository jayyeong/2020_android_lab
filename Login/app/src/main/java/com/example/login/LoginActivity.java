package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText _id,_pass;
    private Button btn_login,btn_register;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _id=findViewById(R.id._id);
        _pass=findViewById(R.id._pass);
        btn_register=findViewById(R.id.btn_register);
        btn_login=findViewById(R.id.btn_login);

        btn_register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), ChooseActivity.class);
                startActivity(intent);
            }
        });
    /*
        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                String userID = _id.getText().toString();
                String userPASS = _pass.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success =jsonObject.getBoolean("success");

                            if(success){
                                btn_register.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view){
                                        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                                        startActivity(intent);
                                    }
                                });
                            }

                        }
                    }
                };

           }
        });

     */
    }
}
