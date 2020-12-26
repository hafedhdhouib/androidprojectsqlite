package com.example.tp6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Supprime extends AppCompatActivity {
Button sup;
EditText login;
    MyDataBase mydatabase = new MyDataBase(Supprime.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supprime);
        login=(EditText) findViewById(R.id.log_in);
        sup=(Button)findViewById(R.id.supprimer);
        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mydatabase.checkUtilisateur(login.getText().toString())) {
                    mydatabase.deleteUtilisateur(login.getText().toString());
                    Intent inti = new Intent(Supprime.this, Gere.class);
                    startActivity(inti);
                }
else Toast.makeText(Supprime.this,"login introvable",Toast.LENGTH_LONG).show();
            }
        });

    }
}