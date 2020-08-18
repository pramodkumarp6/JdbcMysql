package com.android.mysql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.mysql.service.LoginUser;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private Button Signup;
    private Button reg;
    private Button forget;
    private Button signin;
    private EditText etuser;
    private EditText etpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login");
        reg = findViewById(R.id.Register);
        forget = findViewById(R.id.forgetuser);
        Signup= findViewById(R.id.buttonLogin);
        etuser = findViewById(R.id.editTextemail);
        etpass = findViewById(R.id.editTextPassword);
        reg.setOnClickListener(this);
        forget.setOnClickListener(this);
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etuser.getText().toString().trim();
                String pass = etpass.getText().toString().trim();


                login();
            }
        });
    }
    public void login(){
        String email = etuser.getText().toString().trim();
        String pass = etpass.getText().toString().trim();
        Log.d(pass,"etpass");
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Enter the valid Mail Address", Toast.LENGTH_LONG).show();
            return;
        }





        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Please enter Password", Toast.LENGTH_LONG).show();
            return;
        }
        LoginUser loginUser = new LoginUser(this);
        loginUser.execute("data",email,pass);



    }





    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.Register:
                Intent intent = new Intent(Login.this, Register.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            case R.id.forgetuser:
                Intent forget = new Intent(Login.this, ForgetUser.class);
                forget.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                forget.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(forget);
                break;

        }
    }
}