package com.example.practica09quinterosotomayoresteban;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class ControladorBD extends SQLiteOpenHelper {

    public ControladorBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table guardabosques (numguardabosques int primary key, nombre text, apellidos text, sueldo null)";
        try {
            db.execSQL(sql);
            sql = "create table bosques (numbosque int primary key, nombre text)";
            db.execSQL(sql);
        }catch (SQLException e){
            Log.e("ERROR", "Error" + String.valueOf(e.getMessage()));
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
