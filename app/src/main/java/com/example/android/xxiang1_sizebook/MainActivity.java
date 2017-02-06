package com.example.android.xxiang1_sizebook;


import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;

import android.view.Menu;
import android.view.MenuItem;

import android.widget.AdapterView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static android.R.attr.data;


public class MainActivity extends AppCompatActivity {


    private static final String FILENAME = "file.sav";

    private ListView historyList;
    private TextView totalCounts;

    /**
     * The Long clicked item index.
     */
    int longClickedItemIndex;
    private ArrayList<Person> PersonList = new ArrayList<>();
    private ArrayAdapter<Person> adapter;

    private static final int ADD_PERSON_RESULT_CODE = 0;

    private static final int DELETE_PERSON_RESULT_CODE = 1;
    private static final int EDIT_PERSON_RESULT_CODE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //total counts to show number of current records
        totalCounts = (TextView) findViewById(R.id.total_counts);

        //list view for all records
        historyList = (ListView) findViewById(R.id.record_lists);


        //Add Button to open AddPerson Activity
        Button addNew = (Button) findViewById(R.id.add);
        addNew.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddPerson.class);
                startActivityForResult(intent, ADD_PERSON_RESULT_CODE);
            }
        });

        //click item to go to ViewPerson activity, where you can view details of selected item;
        historyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Intent intent = new Intent(MainActivity.this, ViewPerson.class);

                Gson gson = new Gson();
                String person = gson.toJson(PersonList.get(pos));
                intent.putExtra("view", person);
                intent.putExtra("pos", pos);
                startActivityForResult(intent, DELETE_PERSON_RESULT_CODE);


            }
        });

        registerForContextMenu(historyList);
        historyList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                longClickedItemIndex = i;

                return false;
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter = new ArrayAdapter<Person>(this, R.layout.entry, PersonList);
        historyList.setAdapter(adapter);
        loadFromFile();
        totalCounts.setText(String.format("Total Records:  %s", PersonList.size()));

    }

    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);
        menu.add(Menu.NONE, EDIT_PERSON_RESULT_CODE, menu.NONE, "Edit");

    }

    //Go to EditPerson activity if long clicked item
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case EDIT_PERSON_RESULT_CODE:

                Intent intent = new Intent(MainActivity.this, EditPerson.class);
                Gson gson = new Gson();
                String person = gson.toJson(PersonList.get(longClickedItemIndex));
                intent.putExtra("edit", person);
                intent.putExtra("pos", PersonList.get(longClickedItemIndex));
                startActivityForResult(intent, EDIT_PERSON_RESULT_CODE);
                saveInFile();
                break;

        }
        return super.onContextItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_PERSON_RESULT_CODE) {
            if (resultCode == MainActivity.RESULT_OK) {
                Person person = (Person) data.getExtras().getSerializable("newPerson");
                PersonList.add(person);
                adapter.notifyDataSetChanged();
                saveInFile();

            }
        }

        if (requestCode == DELETE_PERSON_RESULT_CODE) {
            if (resultCode == MainActivity.RESULT_OK) {
                int position = data.getIntExtra("pos", -1);
                PersonList.remove(position);
                adapter.notifyDataSetChanged();
                saveInFile();
            }
        }
        if (requestCode == EDIT_PERSON_RESULT_CODE) {
            if (resultCode == MainActivity.RESULT_OK) {
                Person person = (Person) data.getExtras().getSerializable("editPerson");
                int position = data.getIntExtra("position", -1);
                PersonList.add(person);
                PersonList.remove(position);
                adapter.notifyDataSetChanged();
                saveInFile();
            }
        }
    }


    //Taken from Lonely Tweeter: https://github.com/Aquafina99/lonelyTwitter
    // 2017-02-02 23:30

    /**
     * Retrieves JSON data from file and store in PersonList
     */
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            //Taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2017-02-02 23:36

            Type listType = new TypeToken<ArrayList<Person>>() {
            }.getType();
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

    //Taken from Lonely Tweeter: https://github.com/Aquafina99/lonelyTwitter
    // 2017-02-02 23:30
    /**
     * Store PersonList data as JSON format to file
     */
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
