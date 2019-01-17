package com.csce.hamstersftw.hamsterhelp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class emailSearchPage extends AppCompatActivity implements View.OnClickListener {
    private Button SearchButton;

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
            startActivity(new Intent(this, ResultPage.class));
        }
    }
}