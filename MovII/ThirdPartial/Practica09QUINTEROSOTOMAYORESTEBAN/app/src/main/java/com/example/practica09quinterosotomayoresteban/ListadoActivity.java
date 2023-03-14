package com.example.practica09quinterosotomayoresteban;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ListadoActivity extends AppCompatActivity {

    //Instancias de componentes
    private TextView etListado;
    //Instancia del controlador
    ControladorBD admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        //Asociar la instancia con el componente
        etListado = (TextView) findViewById(R.id.txtDetalle);
        //Creación de la base de datos, de manera local, cuyo parametros son:
        //contexto de la aplicación, nombre de la BD, versión
        admin = new ControladorBD(this, "empresapatito.db",null, 1);
        //Define el modo de acceso a la BD
        SQLiteDatabase bd = admin.getReadableDatabase();
        //Instancia del apuntador al registro de busqueda
        Cursor registro = bd.rawQuery("select * from guardabosques ", null);
        //Variable para la cantidad de registro obtenidos
        int n = registro.getCount();//Variable para control de datos en el TextView
        int nr=1;
        //Valido que existan registros de la BS
        if(n > 0){
            //Mover el cursor al inicio de los registro obtenidos
            registro.moveToFirst();
            //Ciclo repetitivo para colocar la información dentro del TextView
            do{
                etListado.setText(etListado.getText() + "\nRegistro #"+ nr);
                etListado.setText(etListado.getText() + "\nNúmero: "+ registro.getString(0));
                etListado.setText(etListado.getText() + "\nNombre: "+ registro.getString(1));
                etListado.setText(etListado.getText() + "\nApellidos: "+ registro.getString(2));
                etListado.setText(etListado.getText() + "\nSueldo: "+ registro.getString(3));
                etListado.setText(etListado.getText() + "\n");
            }while(registro.moveToNext()); //Si existen más registros
        }else{
            //Mensaje informativo que no hay campos
            Toast.makeText(this, "Sin registro de GuardaBosques.", Toast.LENGTH_SHORT).show();
        }
        //Cerrando la BD
        bd.close();
    }

    public void regresarPrincipal(View view){
        /*Intent intent = new Intent(getApplicationContext(), GestionActivity.class);
        startActivity(intent);
        finish();*/
        this.finish();
    }//regresarPrincipal
}