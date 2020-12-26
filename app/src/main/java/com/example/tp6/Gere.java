package com.example.tp6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Gere extends AppCompatActivity {
Button ajouter,supprimer,main;
ListView ls;
    MyDataBase mydatabase;
    ArrayList<String>listitem;
    ArrayAdapter adapter;
    Utlisateur utlisateur=new Utlisateur();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gere);
        ls=(ListView)findViewById(R.id.listView);
        supprimer=(Button)findViewById(R.id.supprimer);
        ajouter=(Button)findViewById(R.id.ajouter);
        main=(Button)findViewById(R.id.Main);
        mydatabase = new MyDataBase(Gere.this);
        listitem=new ArrayList<>();
        ViewData();
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text=ls.getItemAtPosition(0).toString();
            }
        });
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
Intent n = new Intent(Gere.this,Ajouter.class);
startActivity(n);
            }
        });
        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(Gere.this,Supprime.class);
                startActivity(n);
            }
        });
   main.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           Intent n=new Intent(Gere.this,MainActivity.class);
           startActivity(n);
       }
   });
    }


    private void ViewData() {
        Cursor cursor=mydatabase.affiche();
        if (cursor.getCount()!=0){
        while (   cursor.moveToNext()){
                listitem.add(cursor.getString(0));
                adapter =new ArrayAdapter(this, android.R.layout.simple_list_item_1,listitem);
                ls.setAdapter(adapter);
            }
        }
    }
}