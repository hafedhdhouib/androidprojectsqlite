package com.example.tp6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    MyDataBase mydatabase = new MyDataBase(Activity2.this);
    Utlisateur utlisateur=new Utlisateur();
    TextView ch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        Spinner spinner = (Spinner) findViewById(R.id.color);
        ArrayAdapter adapter = ArrayAdapter.createFromResource
                (this, R.array.Color_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        Intent init=getIntent();

        String user =init.getStringExtra("login");
        utlisateur= mydatabase.getUtilisateur(user);

        Toast.makeText(Activity2.this,utlisateur.getNom()+" "+utlisateur.getPrenom(),Toast.LENGTH_LONG).show();


    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view,int pos, long id)  {
        if (!(parent.getItemAtPosition(pos).toString().equals("choisir un coleur")))
        {   String color = parent.getItemAtPosition(pos).toString();
          }}

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}