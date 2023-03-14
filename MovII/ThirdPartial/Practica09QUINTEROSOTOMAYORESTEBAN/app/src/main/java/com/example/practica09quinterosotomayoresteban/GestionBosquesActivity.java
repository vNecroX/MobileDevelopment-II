package com.example.practica09quinterosotomayoresteban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class GestionBosquesActivity extends AppCompatActivity {

    private EditText etNumBosque, etNombre;

    ControladorBD admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_bosques);

        etNumBosque = findViewById(R.id.txtNumBosque);
        etNombre = findViewById(R.id.txtNombreBosque);

        admin = new ControladorBD(this, "empresapatito.db", null, 1);
    }

    public void registrarBosque(View view){
        SQLiteDatabase bd = admin.getWritableDatabase();

        String nump = etNumBosque.getText().toString();
        String nomp = etNombre.getText().toString();

        if(!nump.isEmpty() && !nomp.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("numbosque", nump);
            registro.put("nombre", nomp);

            if(bd != null){
                try{
                    bd.insert("bosques", null, registro);
                }catch (SQLException e){
                    Log.e("Exception", "Error: "+String.valueOf(e.getMessage()));
                }
                bd.close();
            }
            etNumBosque.setText("");
            etNombre.setText("");
            etNumBosque.requestFocus();

            Toast.makeText(this, "Bosque registrado", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Debes registrar primero los datos", Toast.LENGTH_SHORT).show();
        }
    }//registrarBosque

    public void buscarBosque(View view){
        SQLiteDatabase bd = admin.getReadableDatabase();
        String nump = etNumBosque.getText().toString();

        if(!nump.isEmpty()){
            Cursor fila = bd.rawQuery("select nombre from bosques where numbosque=" + nump, null);

            if(fila.moveToFirst()){
                etNombre.setText(fila.getString(0));
                bd.close();
            }else{
                Toast.makeText(this, "Número de Bosque no existe", Toast.LENGTH_SHORT).show();
                etNumBosque.setText("");
                etNumBosque.requestFocus();
                bd.close();
            }//else-if fila
        }else{
            Toast.makeText(this, "Ingreso número de Bosque", Toast.LENGTH_SHORT).show();
            etNumBosque.requestFocus();
        }//else
    }//buscarBosque

    public void actualizarBosque(View view){
        SQLiteDatabase bd = admin.getWritableDatabase();

        String nump = etNumBosque.getText().toString();
        String nomp = etNombre.getText().toString();

        if(!nump.isEmpty() && !nomp.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("numbosque", nump);
            registro.put("nombre", nomp);

            int cantidad = 0;
            cantidad = bd.update("bosques", registro, "numbosque="+nump, null);
            bd.close();

            etNumBosque.setText("");
            etNombre.setText("");
            etNumBosque.requestFocus();
            if(cantidad > 0)
                Toast.makeText(this, "Datos del Bosque actualizados", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "El número de Bosque no existe", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Debes registrar primero los datos", Toast.LENGTH_SHORT).show();
        }
    }//actualizarBosques

    public void eliminarBosque(View view){
        SQLiteDatabase bd = admin.getReadableDatabase();
        String nump = etNumBosque.getText().toString();

        if(!nump.isEmpty()){
            int cantidad = 0;
            cantidad = bd.delete("bosques", "numbosque="+nump, null);
            bd.close();

            etNumBosque.setText("");
            etNombre.setText("");
            etNumBosque.requestFocus();
            if(cantidad > 0)
                Toast.makeText(this, "Datos del bosque eliminados", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "El número de bosque no existe", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Ingreso número de bosque", Toast.LENGTH_SHORT).show();
            etNumBosque.requestFocus();
        }//else
    }//eliminarBosque

    public void listarRegistros(View view){
        Intent intent = new Intent(this,ListadoBosquesActivity.class);
        startActivity(intent);
    }
}