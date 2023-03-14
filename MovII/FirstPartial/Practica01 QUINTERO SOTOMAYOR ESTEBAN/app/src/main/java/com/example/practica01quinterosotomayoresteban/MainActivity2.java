package com.example.practica01quinterosotomayoresteban;

import static com.example.practica01quinterosotomayoresteban.MainActivity.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {
    EditText editTextName, editTextSecName, editTextData;
    Button buttonDate, buttonTime, buttonReg;

    private int diaAlarma = 0;
    private int horaAlarma = 0;
    private int minutoAlarma =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editTextName = findViewById(R.id.editTextName);
        editTextSecName = findViewById(R.id.editTextSecName);
        editTextData = findViewById(R.id.editTextData);

        editTextName.setText(user.getName());

        buttonDate = findViewById(R.id.buttonDate);
        buttonTime = findViewById(R.id.buttonTime);
        buttonReg = findViewById(R.id.buttonReg);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "NOTIFICATION")
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle("Hola!")
                .setContentText("Usted ha registrado su cita!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

        buttonDate.setOnClickListener(view -> setDate() );
        buttonTime.setOnClickListener(view -> setTime() );

        buttonReg.setOnClickListener(view -> {
            notificationManager.notify(100, builder.build());
            regMeeting(); });
    }

    private void setDate(){
        Calendar horarioHoy = Calendar.getInstance();
        horarioHoy.setTimeInMillis(System.currentTimeMillis());

        int anioActual = horarioHoy.get(Calendar.YEAR);
        int mesActual = horarioHoy.get(Calendar.MONTH);
        int diaActual = horarioHoy.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity2.this, new
                DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        diaAlarma = i2;
                    }
                },anioActual, mesActual, diaActual);
        datePickerDialog.setTitle("Fecha de cita");
        datePickerDialog.show();
    }

    private void setTime(){
        Calendar horarioHoy = Calendar.getInstance();
        horarioHoy.setTimeInMillis(System.currentTimeMillis());

        int horaActual = horarioHoy.get(Calendar.HOUR_OF_DAY);
        int minutoActual = horarioHoy.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity2.this, new
                TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        horaAlarma = i;
                        minutoAlarma = i1;
                    }
                }, horaActual, minutoActual, true);
        timePickerDialog.setTitle("Horario de cita");
        timePickerDialog.show();
    }

    private void regMeeting(){
        throwNotification();

        String cita;
        cita = editTextName.getText().toString() + " " + editTextSecName.getText().toString();

        printMssg("Cita registrada a: " + cita);
        printMssg("Fecha de Cita: " + diaAlarma);
        printMssg("Horario de Cita: " + horaAlarma + ":" + minutoAlarma);

        setAlarmOnSystem("CITA CON NUTRIOLOGO", horaAlarma, minutoAlarma);
        editTextName.setText("");
        editTextSecName.setText("");
        editTextData.setText("Horario de cita");
    }

    private void throwNotification() {
        CharSequence name = "channel";
        String description = "Channel for notification";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel notificationChannel = new NotificationChannel("NOTIFICATION", name, importance);
        notificationChannel.setDescription(description);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(notificationChannel);
    }

    private void setAlarmOnSystem(String mensaje, int hora, int minuto){
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, mensaje)
                .putExtra(AlarmClock.EXTRA_HOUR, hora)
                .putExtra(AlarmClock.EXTRA_MINUTES, minuto);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 10);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    private void printMssg(String s){
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }
}