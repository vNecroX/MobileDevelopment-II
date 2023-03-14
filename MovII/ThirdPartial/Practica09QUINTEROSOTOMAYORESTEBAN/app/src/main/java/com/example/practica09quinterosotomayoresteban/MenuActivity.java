package com.example.practica09quinterosotomayoresteban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void gestionGuardaBosques(View view){
        Intent intent = new Intent(this, GestionActivity.class);
        startActivity(intent);
    }

    public void gestionBosques(View view){
        Intent intent = new Intent(this,GestionBosquesActivity.class);
        startActivity(intent);
    }

    public void cerrarSesion(View view){
        finish();
    }
}