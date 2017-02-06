package com.example.android.xxiang1_sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EditPerson extends AppCompatActivity {

    String p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person);

        Bundle bundle = getIntent().getExtras();
        final int position = bundle.getInt("pos");

        Intent intent = getIntent();
        p = intent.getStringExtra("edit");
        Gson gson = new Gson();
        final Person person = gson.fromJson(p, Person.class);

        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final Calendar date = person.getDate();

        final EditText nameField = (EditText) findViewById(R.id.name_edit);
        nameField.setText(person.getName());
        final String name = nameField.getText().toString();

        final EditText neck = (EditText) findViewById(R.id.edit_neck);
        neck.setText(person.getNeck());
        final EditText bust = (EditText) findViewById(R.id.edit_bust);
        bust.setText(person.getBust());
        final EditText waist = (EditText) findViewById(R.id.edit_waist);
        waist.setText(person.getWaist());
        final EditText hip = (EditText) findViewById(R.id.edit_hip);
        hip.setText(person.getHip());
        final EditText inseam = (EditText) findViewById(R.id.edit_inseam);
        inseam.setText(person.getInseam());
        final EditText chest = (EditText) findViewById(R.id.edit_chest);
        chest.setText(person.getChest());
        final EditText comment = (EditText) findViewById(R.id.comment_edit);
        comment.setText(person.getComment());

        final EditText editDate = (EditText) findViewById(R.id.date_edit);
        editDate.setText(sdf.format(date.getTime()));

        Button saveButton = (Button) findViewById(R.id.Save);

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (nameField.getText().toString().isEmpty()){
                    nameField.setError("Name can't be empty");
                }

                else {
                    person.setName(nameField.getText().toString());
                    person.setNeck(neck.getText().toString());
                    person.setBust(bust.getText().toString());
                    person.setWaist(waist.getText().toString());
                    person.setHip(hip.getText().toString());
                    person.setInseam(inseam.getText().toString());
                    person.setChest(chest.getText().toString());
                    person.setComment(comment.getText().toString());

                    //person.setDate(date.getTime());

                    Intent intent = new Intent();
                    intent.putExtra("editPerson", person);
                    intent.putExtra("position", position);
                    setResult(MainActivity.RESULT_OK, intent);
                    finish();
                }

            }
        });

    }
}
