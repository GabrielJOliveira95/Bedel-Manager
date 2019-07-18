package com.mensagemtoast.oliveiragabriel.bedelmanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Layout;
import android.util.Log;
import android.widget.LinearLayout;



import java.util.ArrayList;
import java.util.List;

public class RecyclerView extends AppCompatActivity {


    private RecyclerAdapter adapter;
    private android.support.v7.widget.RecyclerView recyclerView;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        try {

            //database
            sqLiteDatabase = openOrCreateDatabase("tarefas_bd", MODE_PRIVATE, null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS tarefas_table(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, sala_bd VARCHAR, equipamento_bd VARCHAR, numero_do_data_bd VARCHAR, horario_bd VARCHAR)");


            recyclerView = findViewById(R.id.recyclerView);
            android.support.v7.widget.RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            Bundle bundle = getIntent().getExtras();
            String sala = bundle.getString("sala");
            String equipamento = bundle.getString("equipamento");
            String numero_do_data = bundle.getString("numero_do_data");
            String horario = bundle.getString("horario");

            salvarDados(sala, equipamento, numero_do_data, horario);

            adapter = new RecyclerAdapter(dados());
            recyclerView.setAdapter(adapter);



        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void salvarDados(String sala, String equipamento, String numero_do_data, String horario) {

        try {
            sqLiteDatabase.execSQL("INSERT INTO tarefas_table(sala_bd, equipamento_bd, numero_do_data_bd, horario_bd) VALUES('"+sala+"', '"+equipamento+"', '"+numero_do_data+"', '"+horario+"')");



        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public List<RecyclerList> dados() {
        List<RecyclerList> rc = new ArrayList<>();

        try {

       Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tarefas_table ORDER BY id DESC", null);
        int sala_int = cursor.getColumnIndex("sala_bd");
        int equipamento_int = cursor.getColumnIndex("equipamento_bd");
        int numero_do_data_int = cursor.getColumnIndex("numero_do_data_bd");
        int horario_int = cursor.getColumnIndex("horario_bd");
        cursor.moveToFirst();


       while (cursor != null) {

            Log.i("Sala", cursor.getString(sala_int));
            Log.i("Equipamento", cursor.getString(equipamento_int));
            Log.i("Data", cursor.getString(numero_do_data_int));
            Log.i("Horario", cursor.getString(horario_int));

            String salaString = cursor.getString(sala_int);

            String equipamentoString = cursor.getString(equipamento_int);

            String numero_do_data_string = cursor.getString(numero_do_data_int);

            String horarioString = cursor.getString(horario_int);

            rc.add(new RecyclerList(salaString, equipamentoString, numero_do_data_string, horarioString));
            cursor.moveToNext();

        }


       }catch (Exception e){
            e.printStackTrace();
        }
        return rc;
    }}