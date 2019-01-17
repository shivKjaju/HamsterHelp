package com.csce.hamstersftw.hamsterhelp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class tagSearchPage extends AppCompatActivity implements View.OnClickListener {
    private Button SearchButton;
    private EditText searchString;
    private String Tags;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_result_page);
        SearchButton = findViewById(R.id.TagButton);
        SearchButton.setOnClickListener(this);

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(tagSearchPage.this,
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

    @Override
    public void onClick(View view) {
        if (view == SearchButton) {

            Intent i = new Intent(this, ResultPage.class);
//            searchString = findViewById(R.id.TagSearchBar);
//            String searchText = searchString.getText().toString().trim();
            Bundle bundle = new Bundle();
            bundle.putString("Tag",Tags);
            i.putExtras(bundle);
            startActivity(i);

        }
    }
}

