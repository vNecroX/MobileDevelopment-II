package com.example.practica08quinterosotomayoresteban;

import static android.util.Half.EPSILON;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class gyroscopeActivity extends AppCompatActivity implements SensorEventListener {

    private TextView gyroTextViewX, gyroTextViewY, gyroTextViewZ;
    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);

        gyroTextViewX = findViewById(R.id.txtGyroX);
        gyroTextViewY = findViewById(R.id.txtGyroY);
        gyroTextViewZ = findViewById(R.id.txtGyroZ);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged(SensorEvent event) {
        gyroTextViewX.setText("x: " + (event.values[0]));
        gyroTextViewY.setText("y: " + (event.values[1]));
        gyroTextViewZ.setText("z: " + (event.values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}