package com.example.practica04quinterosotomayoresteban;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class ActivityMediaPlayer extends AppCompatActivity {
    Button play, stop;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);

        play = findViewById(R.id.activity_media_play);
        stop = findViewById(R.id.activity_media_stop);

        play.setOnClickListener(view -> play());
        stop.setOnClickListener(view -> stop());
    }

    private void play(){
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.chvrches);
        try{
            mediaPlayer.setDataSource(ActivityMediaPlayer.this, uri);
            mediaPlayer.prepare();
            mediaPlayer.start();
            printMessage("Comienza reproducción. .");
        }catch(IOException e){ printMessage("Error al reproducir: " + e.getMessage()); }
    }

    private void stop(){
        if((mediaPlayer != null) && (mediaPlayer.isPlaying())){
            mediaPlayer.stop();
            mediaPlayer = null;
            printMessage("Se detiene reproducción. .");
        }
    }

    private void printMessage(String s){
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }
}