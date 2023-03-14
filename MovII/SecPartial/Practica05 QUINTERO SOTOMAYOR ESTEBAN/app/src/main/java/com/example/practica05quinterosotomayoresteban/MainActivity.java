package com.example.practica05quinterosotomayoresteban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonMenu1, buttonMenu2;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonMenu1 = findViewById(R.id.buttonMenu1);
        buttonMenu2 = findViewById(R.id.buttonMenu2);

        buttonMenu1.setOnClickListener(view -> lector());
        buttonMenu2.setOnClickListener(view -> camara());
    }

    private void lector(){
        intent = new Intent(getApplicationContext(), MainActivity2.class);
        startActivity(intent);
    }

    private void camara(){
        intent = new Intent(getApplicationContext(), MainActivity3.class);
        startActivity(intent);
    }
}