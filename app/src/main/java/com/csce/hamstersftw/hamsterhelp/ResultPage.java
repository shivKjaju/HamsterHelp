package com.csce.hamstersftw.hamsterhelp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;

public class ResultPage extends AppCompatActivity {
     Databasehelper helper = new Databasehelper(this);
     private String TagPerson = " Tag not found";
    ArrayAdapter<String>  ListSearch;
    ArrayList<Userinfo>TagPersonalInformation;
    String Tags ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page2);
        Bundle bundle = getIntent().getExtras();
         Tags = bundle.getString("Tag");
        //ArrayList<String> FirstNameList = new ArrayList<String>();


        TagPerson = Tags ;
        //String test = "Duc";
        TagPersonalInformation = helper.serachTagWithTheObject(Tags);
       // FirstNameList = helper.serachTag(Tags);

        PersonListAdapter adapter = new PersonListAdapter(this, R.layout.adapter_view_layout, TagPersonalInformation);
//         ListSearch = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
//                FirstNameList);
        ListView ListSearchView = (ListView) findViewById(R.id.ListView);
        ListSearchView.setAdapter(adapter);

        ListSearchView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent i = new Intent (view.getContext(),ListViewDisplayPage.class);
                       //String TagName = String.valueOf(parent.getItemAtPosition(position));
                        Bundle bundle = new Bundle();
                        String TagName = TagPersonalInformation.get(position).getFirstName();
                        String Email = TagPersonalInformation.get(position).getEmail();
                        bundle.putString("email",Email);
                        bundle.putString("Person",TagName);
                        bundle.putString("TagsPerson",TagPerson);
                        bundle.putString("Tags",Tags);
                        i.putExtras(bundle);
                        startActivityForResult(i,position);
                    }
                }
        );

    }
}
