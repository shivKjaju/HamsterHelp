package com.csce.hamstersftw.hamsterhelp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private Button buttonSignIn;
    private EditText email;
    private EditText password;
    private TextView signUp;
    private ProgressDialog progressDialog;
    private Databasehelper helper = new Databasehelper(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int i = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email =  findViewById(R.id.email);
        password =  findViewById(R.id.password);
        buttonSignIn =  findViewById(R.id.login);
        signUp = findViewById(R.id.registration);
        buttonSignIn.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        signUp.setOnClickListener(this);
    }

    private int userLogin(){
        int i = 0;
        String emailAddress = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        //progressDialog.setMessage("REACHED1");
        //progressDialog.show();
        if (TextUtils.isEmpty(emailAddress) && TextUtils.isEmpty(pass) ) {
            Toast.makeText(this,"Please enter email address and password",Toast.LENGTH_LONG).show();
            i = 1;
//        progressDialog.setMessage("Taking you in");
//        progressDialog.show();
        }
        else if(TextUtils.isEmpty(emailAddress)){
            Toast.makeText(this,"Please enter email address",Toast.LENGTH_LONG).show();
            i=1;
            //  progressDialog.setMessage("REACHED2");
            //  progressDialog.show();
            // return;
        }
        else if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            i=1;
            //  progressDialog.setMessage("REACHED3");
            // progressDialog.show();
            // return;
        }

        return i;
    }

    @Override
    public void onClick(View view) {
        if(view == buttonSignIn){
            int y = userLogin();
            if (y != 1) {
                String emailAddress = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String password = helper.serachPass(emailAddress);
                if (pass.equals(password)) {
                    startActivity(new Intent(this, homePageActivity.class));
                } else {
                    Toast.makeText(this, "Email address or password is incorrect", Toast.LENGTH_LONG).show();
                }
            }
        }
        if(view == signUp){
            finish();
            startActivity(new Intent( this, RegistrationActivity.class));
        }
    }


}
