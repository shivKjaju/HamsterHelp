package com.csce.hamstersftw.hamsterhelp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class emailSearchPage extends AppCompatActivity implements View.OnClickListener {
    private Button SearchButton;
    private EditText searchString;
    private ProgressDialog progressDialog;
    Databasehelper helper = new Databasehelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        SearchButton = findViewById(R.id.searchButton);
        SearchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

     if (view == SearchButton) {

        Intent i = new Intent(this, EmailResultPage.class);
        searchString = findViewById(R.id.emailSearchBar);
        String searchText = searchString.getText().toString().trim();
        String EmailChecker = helper.EmailChecking(searchText);
        if (EmailChecker == "true") {
            Bundle bundle = new Bundle();
            bundle.putString("Email", searchText);
            i.putExtras(bundle);

            startActivity(i);

        }else{

            Toast.makeText(this,"Email is not valid",Toast.LENGTH_LONG).show();
        }




    }
}
}