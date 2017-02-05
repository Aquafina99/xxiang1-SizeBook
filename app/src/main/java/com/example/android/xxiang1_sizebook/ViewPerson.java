package com.example.android.xxiang1_sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.util.Calendar;

import com.google.gson.Gson;

import static android.R.attr.data;

public class ViewPerson extends AppCompatActivity {


    String p;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_person);

        //Bundle bundle = getIntent().getExtras();

        TextView name = (TextView) findViewById(R.id.show_name);
        TextView date = (TextView) findViewById(R.id.show_date);
        TextView neck = (TextView) findViewById(R.id.show_neck);
        TextView bust = (TextView) findViewById(R.id.show_bust);
        TextView waist = (TextView) findViewById(R.id.show_waist);
        TextView hip = (TextView) findViewById(R.id.show_hip);
        TextView inseam = (TextView) findViewById(R.id.show_inseam);
        TextView chest = (TextView) findViewById(R.id.show_chest);
        TextView comment = (TextView) findViewById(R.id.show_comment);

        //Intent intent = getIntent();
        //Person person = (Person)getIntent().getSerializableExtra("newPerson");
        //pos = intent.getIntExtra("pos", 0);

        Intent intent = getIntent();
        p = intent.getStringExtra("view");
        pos  = intent.getIntExtra("pos", 0);

        Gson gson = new Gson();
        Person person = gson.fromJson(p, Person.class);


        name.setText(person.getName());
        //date.setText(showDetials.getDate());
        neck.setText(String.valueOf(person.getNeck()));
        bust.setText(String.valueOf(person.getBust()));
        waist.setText(String.valueOf(person.getWaist()));
        hip.setText(String.valueOf(person.getHip()));
        inseam.setText(String.valueOf(person.getInseam()));
        chest.setText(String.valueOf(person.getChest()));
        comment.setText(person.getComment());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.edit){
            editPerson();
        }
        if(item.getItemId() == R.id.delete){
            deletePerson();
        }
        return super.onOptionsItemSelected(item);
    }

    public void editPerson(){
        Intent intent = new Intent(this, EditPerson.class);
        intent.putExtra("edit", p);
        intent .putExtra("pos", pos);
        startActivity(intent);
        finish();
    }

    public void deletePerson(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("delete", p);
        intent.putExtra("pos", pos);
        setResult(MainActivity.RESULT_OK, intent);
        startActivity(intent);
        finish();

    }




}
