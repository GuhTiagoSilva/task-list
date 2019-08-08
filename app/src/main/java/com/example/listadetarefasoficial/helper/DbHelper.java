package com.example.listadetarefasoficial.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "appdb";
    public static String TABLE_NAME = "tb_task";
    public static int VERSION = 4;


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (id_task INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL)";

        try {
            sqLiteDatabase.execSQL(sql);
            Log.i("INFO DB: ", "TABLE CREATED");
        } catch (Exception e) {
            Log.i("InfoDB", "ERROR CREATING DATABASE");
        }

    //CRIACAO DAS TABELAS
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        //ALTERACOES QUE EU QUERO FAZER NAS TABELAS

        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME   ;

        try {
            sqLiteDatabase.execSQL(sql);
            onCreate(sqLiteDatabase);
            Log.i("Info DB: ", "UPDATED TABLE");
        } catch (Exception e) {
            Log.i("Error DB: ", "ERROR UPDATING DATABASE");
        }


    }
}
