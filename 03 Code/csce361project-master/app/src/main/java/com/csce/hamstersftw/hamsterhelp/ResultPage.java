package com.csce.hamstersftw.hamsterhelp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ResultPage extends AppCompatActivity {
     Databasehelper helper = new Databasehelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page2);
        Bundle bundle = getIntent().getExtras();
        String Tags = bundle.getString("Tag");
        ArrayList<String> FirstNameList = new ArrayList<String>();
        //String test = "Duc";
        FirstNameList = helper.serachTag(Tags);

        ListAdapter ListSearch = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                FirstNameList);
        ListView ListSearchView = (ListView) findViewById(R.id.ListView);
        ListSearchView.setAdapter(ListSearch);

    }
}
