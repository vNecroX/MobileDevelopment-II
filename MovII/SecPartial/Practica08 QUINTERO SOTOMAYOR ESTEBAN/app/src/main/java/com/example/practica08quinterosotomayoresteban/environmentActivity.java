package com.example.practica08quinterosotomayoresteban;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class environmentActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor sensor;
    Button ligthBtn, tempBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment);

        ligthBtn = findViewById(R.id.activity_environment_luz);
        tempBtn = findViewById(R.id.activity_environment_temperatura);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        ligthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
                if (sensor == null) {
                    Toast.makeText(environmentActivity.this, "No tienes un sensor de Luz", Toast.LENGTH_SHORT).show();
                } else {
                    sensorManager.registerListener(environmentActivity.this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
                }
            }
        });

        tempBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
                if (sensor == null) {
                    Toast.makeText(environmentActivity.this, "No tienes un Sensor de Temperatura", Toast.LENGTH_SHORT).show();

                } else {
                    sensorManager.registerListener(environmentActivity.this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
                }
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float value = event.values[0];
        String message;

        if(event.sensor.getType() == Sensor.TYPE_LIGHT){
            message = value + " unidades luz";
            Toast.makeText(environmentActivity.this, message, Toast.LENGTH_SHORT).show();
        }

        if(event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE){
            message = value + " grados Celsius";
            Toast.makeText(environmentActivity.this, message, Toast.LENGTH_SHORT).show();
        }

        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}