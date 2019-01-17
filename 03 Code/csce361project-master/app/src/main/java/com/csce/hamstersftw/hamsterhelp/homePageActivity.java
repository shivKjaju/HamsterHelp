package com.csce.hamstersftw.hamsterhelp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class homePageActivity extends AppCompatActivity implements OnClickListener{
    private Button tagSearchButton;
    private Button emailSearchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page2);
        emailSearchButton = findViewById(R.id.emailButton);
        tagSearchButton = findViewById(R.id.tagButton);
        emailSearchButton.setOnClickListener(this);
        tagSearchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        if (view == tagSearchButton){
            startActivity(new Intent( this, tagSearchPage.class));
        }
        else if (view == emailSearchButton){
            startActivity(new Intent( this, emailSearchPage.class));
        }
    }

}
