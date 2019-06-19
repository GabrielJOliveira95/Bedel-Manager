package com.mensagemtoast.oliveiragabriel.bedelmanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.view.View.*;

public class MainActivity extends AppCompatActivity {
    private AppCompatEditText login;
    private AppCompatEditText senha;
    private TextInputLayout textInputLayout_login;
    private TextInputLayout textInputLayout_senha;
    private Button button_login;
    private SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {


            login = findViewById(R.id.login);
            senha = findViewById(R.id.password);
            textInputLayout_login = findViewById(R.id.loginLayout);
            textInputLayout_senha = findViewById(R.id.senhaLayout);
            button_login = findViewById(R.id.btn_login);


            button_login.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    validarForm();




                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //verificar se os campos de login e senha estão preenchidos
    private void validarForm() {

        if (login.getText().toString().isEmpty()) {
            textInputLayout_login.setErrorEnabled(true);
            textInputLayout_login.setError("Type your username");
        } else {
            textInputLayout_login.setErrorEnabled(false);
        }


        if (senha.getText().toString().isEmpty()) {
            textInputLayout_senha.setErrorEnabled(true);
            textInputLayout_senha.setError("Type your password");
        } else {
            textInputLayout_senha.setErrorEnabled(false);
            Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this).toBundle();
            Intent intent = new Intent(MainActivity.this, FinalList.class);
            intent.putExtras(bundle);

            startActivity(intent);
        }




    }


}