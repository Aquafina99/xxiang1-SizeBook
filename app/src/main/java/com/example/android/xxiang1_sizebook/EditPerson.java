package com.example.android.xxiang1_sizebook;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * This activity responsible for editing person object
 */
public class EditPerson extends AppCompatActivity {

    String entry;
    int year, month, day;
    private EditText editDate;
    private Calendar dateEditor = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();
        final int position = bundle.getInt("pos");

        Intent intent = getIntent();
        entry = intent.getStringExtra("edit");
        Gson gson = new Gson();
        final Person person = gson.fromJson(entry, Person.class);

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

        editDate = (EditText) findViewById(R.id.date_edit);
        editDate.setFocusable(false);
        editDate.setText(sdf.format(date.getTime()));

        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditPerson.this, myDateListener,
                        year, day,
                        day).show();
            }
        });

        year = date.get(Calendar.YEAR);
        month = date.get(Calendar.MONTH);
        day = date.get(Calendar.DAY_OF_MONTH);

        Button saveButton = (Button) findViewById(R.id.Save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (nameField.getText().toString().isEmpty()) {
                    nameField.setError("Name can't be empty");
                } else {
                    person.setName(nameField.getText().toString());
                    person.setNeck(neck.getText().toString());
                    person.setBust(bust.getText().toString());
                    person.setWaist(waist.getText().toString());
                    person.setHip(hip.getText().toString());
                    person.setInseam(inseam.getText().toString());
                    person.setChest(chest.getText().toString());
                    person.setComment(comment.getText().toString());
                    person.setDate(dateEditor);

                    Intent intent = new Intent();
                    intent.putExtra("editPerson", person);
                    intent.putExtra("position", position);
                    setResult(MainActivity.RESULT_OK, intent);
                    finish();
                }

            }
        });

    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateEditor.set(Calendar.YEAR, year);
            dateEditor.set(Calendar.MONTH, monthOfYear);
            dateEditor.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            editDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
        }
    };

}
