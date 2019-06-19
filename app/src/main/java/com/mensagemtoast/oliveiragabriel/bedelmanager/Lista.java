package com.mensagemtoast.oliveiragabriel.bedelmanager;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Lista extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView listTask;
    private SQLiteDatabase sqLiteDatabase;
    private ArrayList<String> arrayListTask;
    private ArrayList<Integer> arrayListId;
    private ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);


        try {

        sqLiteDatabase = openOrCreateDatabase("taskdatabase", MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS tasktable(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, task VARCHAR NOT NULL)");
        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        listTask = findViewById(R.id.listTask);

        recoveryTask();

        Bundle bundle = getIntent().getExtras();
        String taskIntent =   bundle.getString("task");
        saveTask(taskIntent);
        recoveryTask();


        listTask.setLongClickable(true);
        listTask.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(Lista.this);
                dialog.setTitle("Remove Task");
                dialog.setMessage("Are you sure that you want to delete this task?");
                dialog.setCancelable(true);
                dialog.setNegativeButton("No", null);
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteTask(arrayListId.get(position));
                    }
                });
                dialog.create();
                dialog.show();

                return true;
            }
        });


        }catch (Exception e){
            e.printStackTrace();
        }}


    public void saveTask(String txt){

        if (txt.equals("")){

            Toast.makeText(Lista.this, "Complete all fields", Toast.LENGTH_LONG).show();
        } else {

            sqLiteDatabase.execSQL("INSERT INTO tasktable(task) VALUES('"+txt+"')");
            recoveryTask();

        }



    }

    public void recoveryTask(){

        try {



        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tasktable ORDER BY id DESC", null);
        int recoveredTask = cursor.getColumnIndex("task");
        int recoveredID = cursor.getColumnIndex("id");
        arrayListId = new ArrayList<>();
        arrayListTask = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<String>(Lista.this, android.R.layout.simple_expandable_list_item_1, android.R.id.text1, arrayListTask );
        listTask.setAdapter(arrayAdapter);


        cursor.moveToFirst();

        while (cursor != null){
            Log.i("Resultado", cursor.getString(recoveredTask));
            Log.i("Id", cursor.getString(recoveredID));
            arrayListId.add(Integer.parseInt(cursor.getString(recoveredID)));
            arrayListTask.add(cursor.getString(recoveredTask));
            cursor.moveToNext();
        }} catch (Exception e ){
            e.printStackTrace();
        }



    }

    private void deleteTask(Integer txt){

        try {
        sqLiteDatabase.execSQL("DELETE FROM tasktable WHERE id =" + txt);
        recoveryTask();

        } catch (Exception e){
            e.printStackTrace();
        }

    }




    }






