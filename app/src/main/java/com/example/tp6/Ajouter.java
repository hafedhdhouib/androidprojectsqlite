package com.example.tp6;
//complet
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Ajouter extends AppCompatActivity {
    MyDataBase mydatabase;
    Utlisateur utlisateur=new Utlisateur();
    Button valider;
    EditText nom,prenom,mp,login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);
        nom=(EditText)findViewById(R.id.log);
        prenom=(EditText)findViewById(R.id.prenom);
        login=(EditText)findViewById(R.id.log_in);
        mp=(EditText)findViewById(R.id.mp);
        valider=(Button)findViewById(R.id.valider);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydatabase = new MyDataBase(Ajouter.this);

                 utlisateur=new Utlisateur();
                    utlisateur.setLogin(login.getText().toString());
                    utlisateur.setNom(nom.getText().toString());
                    utlisateur.setPrenom(prenom.getText().toString());
                    utlisateur.setMp(mp.getText().toString());
                    mydatabase.addUtlisateur(utlisateur);
                    nom.setText("");
                login.setText("");
                prenom.setText("");
                mp.setText("");
                Intent inti=new Intent(Ajouter.this,Gere.class);
                startActivity(inti);
                }});
    }}