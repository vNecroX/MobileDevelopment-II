package com.example.practica04quinterosotomayoresteban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonMusic, buttonMedia, buttonVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonMusic = findViewById(R.id.activity_music_player);
        buttonMedia = findViewById(R.id.activity_media_player);
        buttonVideo = findViewById(R.id.activity_video_player);

        buttonMusic.setOnClickListener(view -> activities(buttonMusic.getId()));
        buttonMedia.setOnClickListener(view -> activities(buttonMedia.getId()));
        buttonVideo.setOnClickListener(view -> activities(buttonVideo.getId()));
    }

    private void activities(int buttonId){
        Intent intent = null;

        if(buttonId == buttonMusic.getId())
            intent = new Intent(this, ActivityMusicPlayer.class);
        else if(buttonId == buttonMedia.getId())
            intent = new Intent(this, ActivityMediaPlayer.class);
        else if(buttonId == buttonVideo.getId())
            intent = new Intent(this, ActivityVideoPlayer.class);

        startActivity(intent);
    }
}