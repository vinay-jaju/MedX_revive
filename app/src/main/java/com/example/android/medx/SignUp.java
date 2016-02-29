package com.example.android.medx;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private EditText sUsername,sName,sPhone ,sPassword, sPassAgain, sEmail;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sUsername = (EditText) findViewById(R.id.s_username);
        sPassword = (EditText) findViewById(R.id.s_password);
        sName = (EditText) findViewById(R.id.s_name);
        sPhone = (EditText) findViewById(R.id.s_phone_no);
        sPassAgain = (EditText) findViewById(R.id.s_apassword);
        sEmail = (EditText) findViewById(R.id.s_email);
        btnSignUp = (Button) findViewById(R.id.btn_signup);

        btnSignUp.setOnClickListener(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this, Login.class));
            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_signup:
                String name = sName.getText().toString();
                String username = sUsername.getText().toString();
                String password = sPassword.getText().toString();
                String email = sEmail.getText().toString();
                String match = sPassAgain.getText().toString();
                String age = sPhone.getText().toString();
                if (password.equals(match)) {
                    User user = new User(name, age, username, email, password);
                    registerUser(user);
                } else {
                    Toast.makeText(this, "PASSWORD DOES NOT MATCH", Toast.LENGTH_SHORT).show();
                }
                break;


        }
    }

    private void registerUser(User user) {
        ServerRequests serverRequest = new ServerRequests(this);
        serverRequest.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                Intent loginIntent = new Intent(SignUp.this, Login.class);
                startActivity(loginIntent);
            }
        });
    }
}
