package com.example.practica08quinterosotomayoresteban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        Intent intent = null;
        switch (view.getId()){
            case R.id.activity_main_sensors:
                intent = new Intent(this, sensorsActivity.class);
                break;

            case R.id.activity_main_proximity:
                intent= new Intent(this, proximityActivity.class);
                break;

            case R.id.activity_main_environment:
                intent = new Intent(this, environmentActivity.class);
                break;

            case R.id.activity_main_gyroscope:
                intent = new Intent(this, gyroscopeActivity.class);
                break;

            case R.id.activity_main_step:
                intent = new Intent(this, moveActivity.class);
                break;
        }
        startActivity(intent);
    }
}