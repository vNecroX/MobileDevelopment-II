package com.example.practica01quinterosotomayoresteban;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    ListView listView;
    String product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        /*
        listView = findViewById(R.id.listViewProducts);
        ArrayAdapter<CharSequence> arrayAdapterProducts = ArrayAdapter.createFromResource(
                this,
                R.array.productsArray,
                android.R.layout.simple_list_item_1
        );
        listView.setAdapter(arrayAdapterProducts);

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            if(i == 0) product = "Anillo, 15 pesos!";
            else if (i == 1) product = "Camisa, 80 pesos!";
            else if (i == 2) product = "Gafas, 60 pesos!";
            else if (i == 3) product = "Pantalon, 90 pesos!";
            else if (i == 4) product = "Sombrero, 120 pesos!";

            printMsg(product);
        });
        */
    }

    private void printMsg(String s){
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}