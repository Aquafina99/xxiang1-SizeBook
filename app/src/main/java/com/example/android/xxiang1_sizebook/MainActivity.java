package com.example.android.xxiang1_sizebook;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.AlphabeticIndex;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.EditText;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends AppCompatActivity {


    private static final String FILENAME = "file.sav";

    private ListView historyList;

    private ArrayList<Person> PersonList = new ArrayList<>();
    private ArrayAdapter<Person> adapter;

    private static final int ADD_PERSON_RESULT_CODE = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //list view for all history record
        historyList = (ListView) findViewById(R.id.record_lists);

        //set up Add Button to open AddPerson Activity
        Button addNew = (Button) findViewById(R.id.add);
        addNew.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddPerson.class);
                startActivityForResult(intent, 0);
            }
        });

        historyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Intent intent = new Intent(MainActivity.this, ViewPerson.class);
                intent.putExtra("ViewPerson", (Serializable) historyList.getItemIdAtPosition(pos));
                intent.putExtra("key", pos);
                startActivity(intent);

            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0){
            if (resultCode == MainActivity.RESULT_OK){
                Person person = (Person)data.getExtras().getSerializable("newPerson");
                PersonList.add(person);
                adapter.notifyDataSetChanged();
                saveInFile();

            }
        }

    }

    @Override
    protected void onStart(){
        super.onStart();

        adapter = new ArrayAdapter<Person>(this, R.layout.entry, PersonList);
        historyList.setAdapter(adapter);
        loadFromFile();

    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            //Taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2017-02-02 23:36

            Type listType = new TypeToken<ArrayList<Person>>(){}.getType();
            PersonList = gson.fromJson(in, listType);
            adapter.clear();
            adapter.addAll(PersonList);
            adapter.notifyDataSetChanged();
            fis.close();

        } catch (FileNotFoundException e) {
            PersonList = new ArrayList<Person>();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(PersonList, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO: Handle the Exception properly later
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }


}
