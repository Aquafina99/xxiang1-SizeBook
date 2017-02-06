package com.example.android.xxiang1_sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.google.gson.Gson;


/**
 * The type View person.
 */
public class ViewPerson extends AppCompatActivity {

    /**
     * The P.
     */
    String entry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_person);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        TextView name = (TextView) findViewById(R.id.show_name);
        TextView date = (TextView) findViewById(R.id.show_date);
        TextView neck = (TextView) findViewById(R.id.show_neck);
        TextView bust = (TextView) findViewById(R.id.show_bust);
        TextView waist = (TextView) findViewById(R.id.show_waist);
        TextView hip = (TextView) findViewById(R.id.show_hip);
        TextView inseam = (TextView) findViewById(R.id.show_inseam);
        TextView chest = (TextView) findViewById(R.id.show_chest);
        TextView comment = (TextView) findViewById(R.id.show_comment);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        Intent intent = getIntent();
        entry = intent.getStringExtra("view");


        Gson gson = new Gson();
        Person person = gson.fromJson(entry, Person.class);

        Calendar getDate = person.getDate();

        name.setText(person.getName());
        date.setText(sdf.format(getDate.getTime()));
        neck.setText(person.getNeck());
        bust.setText(person.getBust());
        waist.setText(person.getWaist());
        hip.setText(person.getHip());
        inseam.setText(person.getInseam());
        chest.setText(person.getChest());
        comment.setText(person.getComment());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.delete) {
            deletePerson();
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * Delete person.
     */
    public void deletePerson() {
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("pos");
        Intent intent = new Intent();
        intent.putExtra("pos", position);
        setResult(MainActivity.RESULT_OK, intent);
        finish();


    }


}
