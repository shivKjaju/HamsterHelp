package com.csce.hamstersftw.hamsterhelp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class homePageActivity extends AppCompatActivity implements OnClickListener{
    private Button tagSearchButton;
    private Button emailSearchButton;
    private Button helperCallButton;
    private TextView displayHelper;
    public static String helperFirstName = " ";
    public static String helperLastName = " ";
    private static final int REQUEST_CALL = 1;
    private Button sosCallButton;
    private TextView numberText;
    public static String helperPhoneNumber;
    public static String helperTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page2);
        emailSearchButton = findViewById(R.id.emailButton);
        tagSearchButton = findViewById(R.id.tagButton);
        helperCallButton = findViewById(R.id.helperCallButton);
        displayHelper = findViewById(R.id.currentHelperText);
        displayHelper.setText("Current HamsterBuddy: " + helperFirstName + " " + helperLastName);
        numberText = findViewById(R.id.helperPhoneText);
        numberText.setText("HamsterBuddy's Phone Number : " + helperPhoneNumber);
        emailSearchButton.setOnClickListener(this);
        tagSearchButton.setOnClickListener(this);
        helperCallButton.setOnClickListener(this);
        helperCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(helperPhoneNumber == null){
                    Toast.makeText(homePageActivity.this, "Phone Number is Empty" , Toast.LENGTH_SHORT).show();
                }else{
                    makeCall();
                }
            }
        });
    }

    @Override
    public void onClick(View view){
        if (view == tagSearchButton){
            startActivity(new Intent( this, tagSearchPage.class));
        }
        else if (view == emailSearchButton){
            startActivity(new Intent( this, emailSearchPage.class));
        }
        else if (view == helperCallButton){
            startActivity(new Intent(this, homePageActivity.class));
        }
    }

    public void setHelperFirstName(String firstName){
        helperFirstName = firstName;
    }
    public void setHelperLastName(String lastName){
        helperLastName = lastName;
    }

    private void makeCall() {
        if(ContextCompat.checkSelfPermission(homePageActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(homePageActivity.this, "Requesting Call" , Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(homePageActivity.this,new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        }
        else {
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + helperPhoneNumber)));
            Toast.makeText(homePageActivity.this, "Call Allowed" , Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CALL){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makeCall();
            }else{
                Toast.makeText(this, "Permission DENIED" , Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void setHelperPhoneNumber(String phoneNumber){
        helperPhoneNumber = phoneNumber;
    }

    public void setHelperTag(String tag){
        helperTag = tag;
    }

}
