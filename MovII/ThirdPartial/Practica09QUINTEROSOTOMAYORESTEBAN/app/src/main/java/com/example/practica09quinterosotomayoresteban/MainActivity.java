package com.example.practica09quinterosotomayoresteban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText usuario, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = findViewById(R.id.txtUsuario);
        password = findViewById(R.id.txtPassword);
        this.cargarInicial();
    }

    private void cargarInicial(){
        try{
            SharedPreferences prefs = getSharedPreferences("Login", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("user", "Esteban");
            editor.putString("pass","123");
            editor.commit();
        }catch (Exception e){
        }
    }//cargarInicial

    public void validarLogin(View view){
        try {
            /*
            SharedPreferences prefs = getSharedPreferences("Login", Context.MODE_PRIVATE);
            if(usuario.getText().toString().equals(prefs.getString("user", null)) && password.getText().toString().equals(prefs.getString("pass", null))){
            }
            */

            Toast.makeText(this, "Usuario Valido", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,MenuActivity.class);
            startActivity(intent);
        }catch (Exception e){
        }
    }
}