package com.android.mysql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.mysql.service.BackgroundTask;

public class Register extends AppCompatActivity {
    private EditText editEmail;
    private EditText editText2;
    private EditText uName;
    private EditText lastname;
    private EditText uMobile;
    private EditText uAddress;
    private Button signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Registration");
        editEmail = findViewById(R.id.editTextEmail);
        uName = (EditText) findViewById(R.id.textusername);
        lastname =(EditText) findViewById(R.id.last_name);
        editText2 = findViewById(R.id.editTextPassword);
        uMobile = findViewById(R.id.userMobile);
        uAddress = findViewById(R.id.userAdd);
        signup = findViewById(R.id.buttonSignup);
    }

    public void saveData(View view) {
        String email = editEmail.getText().toString();
        String pass = editText2.getText().toString();
        String username = uName.getText().toString();
        String lastName = lastname.getText().toString();
        Log.d(lastName,"lastName");
        String mobile = uMobile.getText().toString();
        String address = uAddress.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Enter mail Id", Toast.LENGTH_LONG).show();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Enter the valid Mail Address...", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(mobile)) {
            Toast.makeText(this, "Enter Mobile No...", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(address)) {
            Toast.makeText(this, "Enter Address...", Toast.LENGTH_LONG).show();
            return;
        }





        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Please enter username", Toast.LENGTH_LONG).show();
            return;
        }



        if (TextUtils.isEmpty(lastName)) {
            Toast.makeText(this, "Please enter Last Name", Toast.LENGTH_LONG).show();
            return;
        }



        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Please enter Password", Toast.LENGTH_LONG).show();
            return;
        }

        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute("data", email, pass, username,lastName, mobile, address);


    }
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {    int id = menuItem.getItemId();

        if(id== android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(menuItem);


    }

}