package com.example.practica05quinterosotomayoresteban;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    Button buttonCamara;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        buttonCamara = findViewById(R.id.buttonCamara);
        imageView = findViewById(R.id.imageView);

        buttonCamara.setOnClickListener(view -> tomarFoto());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(this, "Tomar foto", Toast.LENGTH_SHORT).show();

        if(requestCode == 1 && resultCode == RESULT_OK){
            Toast.makeText(this, "Foto capturada", Toast.LENGTH_SHORT).show();
            assert data != null;
            Bundle extras = data.getExtras();
            Bitmap imgBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imgBitmap);
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private void tomarFoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(intent,1);
    }
}