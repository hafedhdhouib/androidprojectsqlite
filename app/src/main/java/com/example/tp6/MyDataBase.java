package com.example.tp6;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyDataBase extends SQLiteOpenHelper {
    private static String DATA_BASE_NAME="utilisateurss.db";
    private static int DATA_BASE_VERSION=1;

    private static final String TABLE_UTLISATEURS = "contacts";
    public static final String KEY_ID="id";
    public static final String KEY_LOGIN="login";
    public static final String KEY_NAME="name";
    public static final String KEY_PRENOM="prenom";
    public static final String KEY_MP="mp";
    public static final String CREATE_UTLISATEUR_TABLE =
            "CREATE TABLE "+TABLE_UTLISATEURS+"("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                    +KEY_LOGIN+" TEXT NOT NULL,"+KEY_NAME+" TEXT  NOT NULL,"+KEY_PRENOM+" TEXT NOT NULL,"+KEY_MP+" TEXT NOT NULL"+")";
    public static  final String Drop_CONTACTS_TABLE ="DROP TABLE IF EXISTs "+TABLE_UTLISATEURS;
    private MyDataBase maBaseSQLITE;



    public MyDataBase(@Nullable Context context) {
        super(context, DATA_BASE_NAME,null, DATA_BASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_UTLISATEUR_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Drop_CONTACTS_TABLE);
        onCreate(db);
    }

    public void addUtlisateur(Utlisateur utlisateur){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values=new ContentValues();
        values.put(KEY_LOGIN,utlisateur.getLogin());
        values.put(KEY_NAME,utlisateur.getNom());
        values.put(KEY_MP,utlisateur.getMp());
        values.put(KEY_PRENOM,utlisateur.getPrenom());
        db.insert(TABLE_UTLISATEURS,null,values);
        db.close();
    }
    Utlisateur getUtilisateur(String login){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor =db.query(TABLE_UTLISATEURS,new String[]{
                        KEY_ID,KEY_LOGIN,KEY_MP,KEY_NAME,KEY_PRENOM},KEY_LOGIN+"=?",
                new String[]{String.valueOf(login)},null,null,null,null);
        if (cursor!=null)
            cursor.moveToFirst();
        Utlisateur utlisateur =new Utlisateur(Integer.parseInt(
                cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4));
        return utlisateur;
    }
    boolean checkUtilisateur(String login){

        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor =db.query(TABLE_UTLISATEURS,new String[]{
                        KEY_ID,KEY_LOGIN,KEY_MP,KEY_NAME,KEY_PRENOM},KEY_LOGIN+"=?",
                new String[]{login},null,null,null,null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0){
            return true;
        }
        return false;
    }



    public List<Utlisateur> getAllUtilisateurs(){
        List<Utlisateur> utlisateursList=new ArrayList<Utlisateur>();
        String selectQuery="SELECT * FROM "+TABLE_UTLISATEURS;
        SQLiteDatabase db=maBaseSQLITE.getReadableDatabase();
        Cursor cursor= db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do {

                Utlisateur utlisateur =new Utlisateur();
                utlisateur.setId(Integer.parseInt((cursor.getString(0))));
                utlisateur.setLogin(cursor.getString(1));
                utlisateur.setMp(cursor.getString(2));
                utlisateur.setNom(cursor.getString(3));
                utlisateur.setPrenom(cursor.getString(4));
                utlisateursList.add(utlisateur);
            }while (cursor.moveToNext());
        }return utlisateursList;
    }
    public int updateUtilisateur (Utlisateur utlisateur){
        SQLiteDatabase db=maBaseSQLITE.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(KEY_LOGIN,utlisateur.getLogin());
        values.put(KEY_NAME,utlisateur.getNom());
        values.put(KEY_PRENOM,utlisateur.getPrenom());
        values.put(KEY_MP,utlisateur.getMp());
        return db.update(TABLE_UTLISATEURS,
                values,
                KEY_ID+" =?",
               new String[]{String.valueOf(utlisateur.getId())});
    }
    public void deleteUtilisateur(String log){
        SQLiteDatabase db =this.getWritableDatabase();
db.delete(TABLE_UTLISATEURS,KEY_LOGIN+"=?",new String[]{log});

        db.close();
    }
    public int getUtilisateurCount(){
        String countQuery="SELECT * FROM "+TABLE_UTLISATEURS;
        SQLiteDatabase db=maBaseSQLITE.getReadableDatabase();
        Cursor cursor =db.rawQuery(countQuery,null);
        cursor.close();
        int count = cursor.getCount();
        return count;
    }
public ArrayList<HashMap<String,String>>GetUser(){
    SQLiteDatabase db =maBaseSQLITE.getWritableDatabase();
ArrayList<HashMap<String,String>>usersList=new ArrayList<>();
String query ="SELECT * FROM " + TABLE_UTLISATEURS;
Cursor cursor=db.rawQuery(query,null);
while (cursor.moveToNext()){
    HashMap<String,String> user=new HashMap<>();
    user.put("login",cursor.getString(cursor.getColumnIndex(KEY_LOGIN)));
    user.put("name",cursor.getString(cursor.getColumnIndex(KEY_NAME)));
    user.put("prenom",cursor.getString(cursor.getColumnIndex(KEY_PRENOM)));
usersList.add(user);
}
return usersList;
}
    boolean checkUtilisateur(String login,String pass){

        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor =db.query(TABLE_UTLISATEURS,new String[]{
                        KEY_ID,KEY_LOGIN,KEY_MP,KEY_NAME,KEY_PRENOM},
                KEY_LOGIN+"=? "+"AND "+KEY_MP+" =? ",
                new String[]{login,pass},null,null,null,null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0){
            return true;
        }
        return false;
    }
    public Cursor affiche(){
        SQLiteDatabase db=this.getReadableDatabase();
        String Query="SELECT "+KEY_LOGIN+" FROM "+TABLE_UTLISATEURS;
        Cursor cursor=db.rawQuery(Query,null);
        return cursor;
    }
}

