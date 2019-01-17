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

public class ListViewDisplayPage extends AppCompatActivity implements View.OnClickListener{
    private Button AddRating;
    private Button addHelper;
    Databasehelper helper = new Databasehelper(this);
    homePageActivity homePage = new homePageActivity();
    private static final String TAG = "ListViewDisplayPage";
    String RatingNumber ="3";
    String email;
    String Tags;
    String helperPhoneNumberTag;
    String helperFirstNameTag;
    String helperLastNameTag;
    String helperTag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_display_page);
        AddRating =  findViewById(R.id.RatingButton);
        AddRating.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        String FirstName = bundle.getString("Person");
        String Tag = bundle.getString("TagsPerson");
         email = bundle.getString("email");
         Tags = bundle.getString("Tags");
        ArrayList<String> PersonalInformation = new ArrayList<String>();
        //String test = "Duc";
        PersonalInformation = helper.PersonalInformation(FirstName,Tag,email);
        TextView PersonFirstName = (TextView) findViewById(R.id.Firstname1);
        TextView PersonLastName = (TextView) findViewById(R.id.LastName1);
        TextView PersonEmail = (TextView) findViewById(R.id.Email1);
        TextView PersonMobile = (TextView) findViewById(R.id.PhoneNumber1);

        PersonFirstName.setText("First Name: " + PersonalInformation.get(0));
        PersonLastName.setText("Last Name: " + PersonalInformation.get(1));
        PersonEmail.setText("Email: " + PersonalInformation.get(2));
        PersonMobile.setText("Mobile: " + PersonalInformation.get(3));

        String PersonalFirstName = PersonFirstName.getText().toString();
        String PersonalLastName =  PersonLastName.getText().toString();
        String PersonalEmail = PersonEmail.getText().toString();
        String PersonalMobile = PersonMobile.getText().toString();

        Log.d(TAG,"onCreate: " + PersonalFirstName);
        Log.d(TAG,"onCreate: " + PersonalLastName);
        Log.d(TAG,"onCreate: " + PersonalEmail);
        Log.d(TAG,"onCreate: " + PersonalMobile);

        //Add Helper Function
        addHelper = findViewById(R.id.addHelperTagButton);
        addHelper.setOnClickListener(this);
        helperFirstNameTag = PersonalInformation.get(0);
        helperLastNameTag = PersonalInformation.get(1);
        helperPhoneNumberTag = PersonalInformation.get(3);
        helperTag = PersonalInformation.get(4);
        //Rating Function
        SmileRating smileRating = (SmileRating) findViewById(R.id.smile_Rating);
        smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener() {
            @Override
            public void onSmileySelected(int smiley) {
                switch (smiley){
                    case SmileRating.BAD:
                        Toast.makeText(ListViewDisplayPage.this,"Not Quite Well",Toast.LENGTH_SHORT).show();
                        RatingNumber = "2";
                        break;
                    case SmileRating.GOOD:
                        Toast.makeText(ListViewDisplayPage.this,"Pretty Good",Toast.LENGTH_SHORT).show();
                        RatingNumber = "4";
                        break;
                    case SmileRating.GREAT:
                        Toast.makeText(ListViewDisplayPage.this,"I Like This",Toast.LENGTH_SHORT).show();
                        RatingNumber = "5";
                        break;
                    case SmileRating.OKAY:
                        Toast.makeText(ListViewDisplayPage.this,"It's OKAY",Toast.LENGTH_SHORT).show();
                        RatingNumber = "3";
                        break;
                    case SmileRating.TERRIBLE:
                        Toast.makeText(ListViewDisplayPage.this,"Hmm",Toast.LENGTH_SHORT).show();
                        RatingNumber = "1";
                        break;

                }
            }
        });
//        smileRating.setOnRatingSelectedListener(new SmileRating.OnRatingSelectedListener() {
//            @Override
//            public void onRatingSelected(int level) {
//                Toast.makeText(ListViewDisplayPage.this, "Selected rating" + level, Toast.LENGTH_SHORT).show();
//            }
//        });
    }
    @Override
    public void onClick(View view) {
        if(view == AddRating){
            helper.UpdateRating(email,RatingNumber);
            Intent i = new Intent(this, ResultPage.class);
            Bundle bundle = new Bundle();
            bundle.putString("Tag",Tags);
            i.putExtras(bundle);
            startActivity(i);
        }else if (view == addHelper){
            homePage.setHelperFirstName(helperFirstNameTag);
            homePage.setHelperLastName(helperLastNameTag);
            homePage.setHelperPhoneNumber(helperPhoneNumberTag);
            homePage.setHelperTag(helperTag);
            Intent redirectToHome = new Intent(this, homePageActivity.class);
            startActivity(redirectToHome);
        }
    }
}
