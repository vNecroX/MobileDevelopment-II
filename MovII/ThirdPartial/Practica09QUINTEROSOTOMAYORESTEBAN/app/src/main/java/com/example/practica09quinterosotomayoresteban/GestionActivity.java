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

public class GestionActivity extends AppCompatActivity {

    private EditText etNumGuardaBosques, etNombre, etApellido, etSueldo;

    ControladorBD admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion);

        etNumGuardaBosques = findViewById(R.id.txtNumGuardaBosques);
        etNombre = findViewById(R.id.txtNombre);
        etApellido = findViewById(R.id.txtApellidos);
        etSueldo = findViewById(R.id.txtSueldo);

        admin = new ControladorBD(this, "empresapatito.db", null, 1);
    }

    public void registrarGuardaBosques(View view){
        SQLiteDatabase bd = admin.getWritableDatabase();

        String nump = etNumGuardaBosques.getText().toString();
        String nomp = etNombre.getText().toString();
        String apep = etApellido.getText().toString();
        String suep = etSueldo.getText().toString();

        if(!nump.isEmpty() && !nomp.isEmpty() && !apep.isEmpty() && !suep.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("numguardabosques", nump);
            registro.put("nombre", nomp);
            registro.put("apellido", apep);
            registro.put("sueldo", suep);

            if(bd != null){
                try{
                    bd.insert("guardabosques", null, registro);
                }catch (SQLException e){
                    Log.e("Exception", "Error: "+String.valueOf(e.getMessage()));
                }
                bd.close();
            }
            etNumGuardaBosques.setText("");
            etNombre.setText("");
            etApellido.setText("");
            etSueldo.setText("");
            etNumGuardaBosques.requestFocus();

            Toast.makeText(this, "GuardaBosques registrado", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Debes registrar primero los datos", Toast.LENGTH_SHORT).show();
        }
    }//registrarGuardaBosques

    public void buscarGuardaBosques(View view){
        SQLiteDatabase bd = admin.getReadableDatabase();
        String nump = etNumGuardaBosques.getText().toString();

        if(!nump.isEmpty()){
            Cursor fila = bd.rawQuery("select nombre, apellidos, sueldo from guardabosques where numguardabosques=" + nump, null);

            if(fila.moveToFirst()){
                etNombre.setText(fila.getString(0));
                etApellido.setText((fila.getString(1)));
                etSueldo.setText(fila.getString(2));
                bd.close();
            }else{
                Toast.makeText(this, "Número de GuardaBosques no existe", Toast.LENGTH_SHORT).show();
                etNumGuardaBosques.setText("");
                etNumGuardaBosques.requestFocus();
                bd.close();
            }//else-if fila
        }else{
            Toast.makeText(this, "Ingreso número de GuardaBosques", Toast.LENGTH_SHORT).show();
            etNumGuardaBosques.requestFocus();
        }//else
    }//buscarGuardaBosques

    public void actualizarGuardaBosques(View view){
        SQLiteDatabase bd = admin.getWritableDatabase();

        String nump = etNumGuardaBosques.getText().toString();
        String nomp = etNombre.getText().toString();
        String apep = etApellido.getText().toString();
        String suep = etSueldo.getText().toString();

        if(!nump.isEmpty() && !nomp.isEmpty() && !apep.isEmpty() && !suep.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("numguardabosques", nump);
            registro.put("nombre", nomp);
            registro.put("apellido", apep);
            registro.put("sueldo", suep);

            int cantidad = 0;
            cantidad = bd.update("guardabosques", registro, "numguardabosques="+nump, null);
            bd.close();

            etNumGuardaBosques.setText("");
            etNombre.setText("");
            etApellido.setText("");
            etSueldo.setText("");
            etNumGuardaBosques.requestFocus();
            if(cantidad > 0)
                Toast.makeText(this, "Datos del GuardaBosque actualizados", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "El número de GuardaBosque no existe", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Debes registrar primero los datos", Toast.LENGTH_SHORT).show();
        }
    }//actualizarGuardabosques

    public void eliminarGuardaBosques(View view){
        SQLiteDatabase bd = admin.getReadableDatabase();
        String nump = etNumGuardaBosques.getText().toString();

        if(!nump.isEmpty()){
            int cantidad = 0;
            cantidad = bd.delete("guardabosques", "numguardabosques="+nump, null);
            bd.close();

            etNumGuardaBosques.setText("");
            etNombre.setText("");
            etApellido.setText("");
            etSueldo.setText("");
            etNumGuardaBosques.requestFocus();
            if(cantidad > 0)
                Toast.makeText(this, "Datos del guardabosques eliminados", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "El número de guardabosques no existe", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Ingreso número de guardabosques", Toast.LENGTH_SHORT).show();
            etNumGuardaBosques.requestFocus();
        }//else
    }//eliminarGuardaBosques

    public void listarRegistros(View view){
        Intent intent = new Intent(this,ListadoActivity.class);
        startActivity(intent);
    }
}