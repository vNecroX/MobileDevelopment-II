package com.example.practica05quinterosotomayoresteban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity2 extends AppCompatActivity {

    Button buttonScan;
    EditText editTextResultado, editTextDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        buttonScan = findViewById(R.id.buttonScan);

        editTextResultado = findViewById(R.id.editTextResultado);
        editTextDescripcion = findViewById(R.id.editTextDescripcion);

        buttonScan.setOnClickListener(view -> escanearCodigoBarra());
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(intentResult != null ){
            if(intentResult.getContents() == null)
                printMessage("Lectura cancelada.");
            else{
                printMessage("Datos leídos.");
                editTextResultado.setText(intentResult.getContents());
            }
        }
        else super.onActivityResult(requestCode, resultCode, data);
    }

    private void escanearCodigoBarra(){
        IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity2.this);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setPrompt("Lector - CDP");
        intentIntegrator.setCameraId(0);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setBarcodeImageEnabled(true);
        intentIntegrator.initiateScan();
    }

    private void printMessage(String s){
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}