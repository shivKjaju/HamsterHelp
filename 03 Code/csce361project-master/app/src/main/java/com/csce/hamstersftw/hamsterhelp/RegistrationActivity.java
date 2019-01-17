package com.csce.hamstersftw.hamsterhelp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.app.ProgressDialog;
import android.widget.Spinner;
import android.widget.Toast;


import com.facebook.stetho.Stetho;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{

    private Button register;
    private EditText firstName;
    private EditText lastName;
    private EditText birthDay;
    private EditText mobile;
    private EditText address1;
    private EditText address2;
    private EditText email;
    private EditText pass;
    public String Tags = "Here to Help!";


    Databasehelper helper = new Databasehelper(this);

    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_registration);
        progressDialog = new ProgressDialog(this);
        register = findViewById(R.id.Register1);
        firstName = findViewById(R.id.firstname);
        lastName = findViewById(R.id.lastName);
        birthDay = findViewById(R.id.birthday);
        mobile = findViewById(R.id.mobile);
        address1 = findViewById(R.id.address1);
        address2 = findViewById(R.id.address2);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        register.setOnClickListener(this);

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(RegistrationActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    Tags = "Here to Help!";
                }else if (position == 1){
                    Tags = "I need Help!";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private int checker(){
        int i = 0;
        String firstname = firstName.getText().toString();

        String lastname = lastName.getText().toString();

        String birthday = birthDay.getText().toString().trim();

        String phno = mobile.getText().toString().trim();

        String addres1 = address1.getText().toString();

        String addres2 = address2.getText().toString();

        String Email = email.getText().toString();

        String Password = pass.getText().toString();



        if (TextUtils.isEmpty(firstname) || TextUtils.isEmpty(lastname) || TextUtils.isEmpty(birthday) || TextUtils.isEmpty(phno) || TextUtils.isEmpty(addres1)
                || TextUtils.isEmpty(addres2)|| TextUtils.isEmpty(Email)|| TextUtils.isEmpty(Password)) {
            i = 1;

        }


        return i;
    }
    public void onRegister(View view) {
        Pattern pattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
        Pattern pattern1 = Pattern.compile("\\d{10}");
        Pattern Date = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");
        int CheckEmpty = checker();
        if (CheckEmpty == 1 ){
            progressDialog.setMessage("Please fill all the information");
            progressDialog.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    Intent i=new Intent(RegistrationActivity.this,RegistrationActivity.class);
                    startActivity(i);
                }
            }, 5000);
        }else {


            int ii = 0;
            String firstname = firstName.getText().toString();
            String lastname = lastName.getText().toString();
            String birthday = birthDay.getText().toString().trim();
            String addres1 = address1.getText().toString();
            String addres2 = address2.getText().toString();
            String Email = email.getText().toString();
            String Password = pass.getText().toString();
            Userinfo u = new Userinfo();
            String phno = mobile.getText().toString().trim();
            Matcher matcher = pattern.matcher(phno);
            Matcher matcher1 = pattern1.matcher(phno);
            Matcher DateCheck = Date.matcher(birthday);
            if (!matcher.matches() && !matcher1.matches() && !DateCheck.matches()){
                ii = 1;
                progressDialog.setMessage("Birthday and Phone number are in incorrect form  ");
                progressDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        progressDialog.cancel();
                    }
                }, 5000);
            }
            else if (!matcher.matches() && !matcher1.matches()) {
                ii =1;
                progressDialog.setMessage("Phone number is invalid");
                progressDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        progressDialog.cancel();
                    }
                }, 5000);

            }else if (!DateCheck.matches()){
                ii =1;
                progressDialog.setMessage("Please Enter your Birthday in the correct form mm/dd/yy");
                progressDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        progressDialog.cancel();
                    }
                }, 5000);
            }



            if (ii != 1) {
                u.setFirstName(firstname);
                u.setLastName(lastname);
                u.setBirthDay(birthday);
                u.setMobile(phno);
                u.setAddressLine1(addres1);
                u.setAddressLine2(addres2);
                u.setEmail(Email);
                u.setPassword(Password);
                u.setTag(Tags);
                helper.insertInfo(u);

                Intent i = new Intent(this, Login.class);
                startActivity(i);
            }
        }




    }

    @Override
    public void onClick(View view) {
        onRegister(view);
    }
}

