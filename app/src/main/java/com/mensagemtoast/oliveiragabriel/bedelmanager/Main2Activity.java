package com.mensagemtoast.oliveiragabriel.bedelmanager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private Spinner spinner_andar;
    private Button btnAndar;
    private ImageView close;
    private String andarIntent="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);





        close = findViewById(R.id.close);
        spinner_andar = findViewById(R.id.spinnerAndar);
        btnAndar = findViewById(R.id.btnAndar);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(Main2Activity.this, R.array.andar, R.layout.support_simple_spinner_dropdown_item);
        spinner_andar.setAdapter(arrayAdapter);

        btnAndar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String andarEscolhido = spinner_andar.getSelectedItem().toString();
                Intent intent = new Intent(Main2Activity.this, Lista.class);
                intent.putExtra("floor", andarEscolhido);
                startActivity(intent);

            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(Main2Activity.this);
                dialog.setTitle("Are you sure you want to exit the app?");
                dialog.setCancelable(false);
                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        Toast.makeText(Main2Activity.this, "App closed", Toast.LENGTH_LONG).show();
                    }
                });

                dialog.create();
                dialog.show();


            }
        });



    }


    private void andarEscolhido(String andar){

        switch (andar){
            case "Térreo":
                andarIntent = "Térreo";
                break;
            case  "1°Andar":
                andarIntent = "1°Andar";
                break;
            case "Ala Nova":
                andarIntent = "Ala Nova";
                break;
            case "Mezanino":
                andarIntent = "Mezanino";

        }


    }

}
