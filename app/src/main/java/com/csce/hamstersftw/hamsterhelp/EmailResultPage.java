package com.csce.hamstersftw.hamsterhelp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hsalf.smilerating.SmileRating;

import java.util.ArrayList;

public class EmailResultPage extends AppCompatActivity implements View.OnClickListener{
    Databasehelper helper = new Databasehelper(this);
    homePageActivity homePage = new homePageActivity();
    private static final String TAG = "EmailResultPage";
    String RatingNumber = "3";
    private Button AddRating;
    private Button addHelper;
    String email;
    String helperPhoneNumber;
    String helperFirstName;
    String helperLastName;
    String helperTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_result_page);
        Bundle bundle = getIntent().getExtras();
        email = bundle.getString("Email");
        AddRating =  findViewById(R.id.RatingButton);
        AddRating.setOnClickListener(this);
        ArrayList<String> PersonalInformationByEmail = new ArrayList<String>();
        PersonalInformationByEmail = helper.EmailSearchPersonalInformation(email);
        TextView PersonFirstName = (TextView) findViewById(R.id.Firstname1);
        TextView PersonLastName = (TextView) findViewById(R.id.LastName1);
        TextView PersonEmail = (TextView) findViewById(R.id.Email1);
        TextView PersonMobile = (TextView) findViewById(R.id.PhoneNumber1);
        PersonFirstName.setText("First Name: " + PersonalInformationByEmail.get(0));
        PersonLastName.setText("Last Name: " + PersonalInformationByEmail.get(1));
        PersonEmail.setText("Email: " + PersonalInformationByEmail.get(2));
        PersonMobile.setText("Mobile: " + PersonalInformationByEmail.get(3));
        String PersonalFirstName = PersonFirstName.getText().toString();
        String PersonalLastName =  PersonLastName.getText().toString();
        String PersonalEmail = PersonEmail.getText().toString();
        String PersonalMobile = PersonMobile.getText().toString();
        Log.d(TAG,"onCreate: " + PersonalFirstName);
        Log.d(TAG,"onCreate: " + PersonalLastName);
        Log.d(TAG,"onCreate: " + PersonalEmail);
        Log.d(TAG,"onCreate: " + PersonalMobile);
        //Add Helper Function
        addHelper = findViewById(R.id.addHelperButton);
        addHelper.setOnClickListener(this);
        helperFirstName = PersonalInformationByEmail.get(0);
        helperLastName = PersonalInformationByEmail.get(1);
        helperPhoneNumber = PersonalInformationByEmail.get(3);
        helperTag = PersonalInformationByEmail.get(4);
        //Rating Function
        SmileRating smileRating = (SmileRating) findViewById(R.id.smile_Rating);
        smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley) {
                switch (smiley){
                    case SmileRating.BAD:
                        Toast.makeText(EmailResultPage.this,"Not Quite Well",Toast.LENGTH_SHORT).show();
                        RatingNumber = "2";
                        break;
                    case SmileRating.GOOD:
                        Toast.makeText(EmailResultPage.this,"Pretty Good",Toast.LENGTH_SHORT).show();
                        RatingNumber = "4";
                        break;
                    case SmileRating.GREAT:
                        Toast.makeText(EmailResultPage.this,"I Like This",Toast.LENGTH_SHORT).show();
                        RatingNumber = "5";
                        break;
                    case SmileRating.OKAY:
                        Toast.makeText(EmailResultPage.this,"It's OKAY",Toast.LENGTH_SHORT).show();
                        RatingNumber = "3";
                        break;
                    case SmileRating.TERRIBLE:
                        Toast.makeText(EmailResultPage.this,"Bitches",Toast.LENGTH_SHORT).show();
                        RatingNumber = "1";
                        break;

                }
            }
        });
    }
    @Override
    public void onClick(View view) {
        if(view == AddRating){
            helper.UpdateRating(email,RatingNumber);
            Intent i = new Intent(this, emailSearchPage.class);
//            Bundle bundle = new Bundle();
//            bundle.putString("Email",email);
//            i.putExtras(bundle);
            startActivity(i);
        }else if(view == addHelper){
            homePage.setHelperFirstName(helperFirstName);
            homePage.setHelperLastName(helperLastName);
            homePage.setHelperPhoneNumber(helperPhoneNumber);
            homePage.setHelperTag(helperTag);
            Intent redirectToHome = new Intent(this, homePageActivity.class);
            startActivity(redirectToHome);
        }
    }
}
