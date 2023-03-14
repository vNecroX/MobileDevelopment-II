package com.example.practica01quinterosotomayoresteban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static User user = new User();

    EditText editTextUsername, editTextPssw;
    Button buttonLogin, buttExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPssw = findViewById(R.id.editTextPssw);

        buttonLogin = findViewById(R.id.buttonLogin);
        buttExit = findViewById(R.id.buttonExit);

        buttonLogin.setOnClickListener(view -> verifyLogin());
        buttExit.setOnClickListener(view -> exit());
    }

    private void verifyLogin(){
        String mssg = "x";

        if(TextUtils.isEmpty(editTextUsername.getText())){
            mssg = "Favor de colocar su nombre de usuario!";
            printMssg(mssg);
        }

        if(TextUtils.isEmpty(editTextPssw.getText())){
            mssg = "Favor de colocar su contraseña!";
            printMssg(mssg);
        }

        if(mssg.equals("x")){
            printMssg("Bienvenid@ " + editTextUsername.getText());
            login();
        }
    }

    private void login(){
        user.setName(editTextUsername.getText().toString());
        user.setPssw(editTextPssw.getText().toString());

        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }

    private void printMssg(String s){ Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show(); }

    private void exit(){
        MainActivity.this.finish();
        System.exit(0);
    }
}