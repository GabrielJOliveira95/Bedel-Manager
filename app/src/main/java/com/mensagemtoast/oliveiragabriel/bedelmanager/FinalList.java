package com.mensagemtoast.oliveiragabriel.bedelmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class FinalList extends AppCompatActivity {
    private Toolbar toolbar;
    private AppCompatEditText editText_room;
    private AppCompatEditText editText_data;
    private Spinner spinner_equip;
    private Spinner spinner_schedule;
    private Button btn_save;
    private Button btn_list;
    private String equipR;
    private String scheduleR;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_list);

        try {


            toolbar = findViewById(R.id.toolBar);
            setSupportActionBar(toolbar);
            btn_list = findViewById(R.id.btn_list);
            btn_save = findViewById(R.id.btn_saveTask);
            editText_data = findViewById(R.id.edit_text_data2);
            editText_room = findViewById(R.id.edit_text_room2);
            spinner_equip = findViewById(R.id.spinner_equip2);
            spinner_schedule = findViewById(R.id.spinner_schedule2);

            ArrayAdapter arrayAdapterEquip = ArrayAdapter.createFromResource(FinalList.this, R.array.equipamentos, R.layout.support_simple_spinner_dropdown_item);
            ArrayAdapter arrayAdapterSchedule = ArrayAdapter.createFromResource(FinalList.this, R.array.schedule, R.layout.support_simple_spinner_dropdown_item);
            spinner_schedule.setAdapter(arrayAdapterSchedule);
            spinner_equip.setAdapter(arrayAdapterEquip);

            spinner_equip.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    equipR = spinner_equip.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            spinner_schedule.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    scheduleR = spinner_schedule.getItemAtPosition(position).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            btn_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String totalTask = "Room " + editText_room.getText() + " - " + equipR + " - " + "Data N° " + editText_data.getText() + " - " + "Schedule " + scheduleR;
                    editText_data.setText("");
                    editText_room.setText("");

                    Intent intent = new Intent(FinalList.this, Lista.class);
                    intent.putExtra("task", totalTask);
                    Toast.makeText(FinalList.this, "Task Saved", Toast.LENGTH_LONG).show();

                }
            });

            btn_list.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent2 = new Intent(FinalList.this, Lista.class);
                    startActivity(intent2);
                }
            });


        }catch (Exception e){
        e.printStackTrace();
        }
    }
}
