package com.example.tp6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Auth extends AppCompatActivity {
Button auth;
EditText login,pass;
    MyDataBase mydatabase;
    Utlisateur utlisateur=new Utlisateur();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
    login=(EditText)findViewById(R.id.login);
    pass=(EditText)findViewById(R.id.pass);
    auth=(Button)findViewById(R.id.Button);
    auth.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mydatabase = new MyDataBase(Auth.this);
if (mydatabase.checkUtilisateur(login.getText().toString(),pass.getText().toString())){
    Intent n=new Intent(Auth.this,Activity2.class);
    String s=login.getText().toString();
    n.putExtra("login",s);
    startActivity(n);

}
else {
    Toast.makeText(Auth.this,"login ou pass incorect",Toast.LENGTH_LONG).show();
}

        }
    });

    }
}