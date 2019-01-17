package com.csce.hamstersftw.hamsterhelp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class tagSearchPage extends AppCompatActivity implements View.OnClickListener {
    private Button SearchButton;
    private EditText searchString;
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

            Intent i = new Intent(this, ResultPage.class);
            searchString = findViewById(R.id.emailSearchBar);
            String searchText = searchString.getText().toString().trim();
            Bundle bundle = new Bundle();
            bundle.putString("Tag",searchText);
            i.putExtras(bundle);
            startActivity(i);

        }
    }
}

