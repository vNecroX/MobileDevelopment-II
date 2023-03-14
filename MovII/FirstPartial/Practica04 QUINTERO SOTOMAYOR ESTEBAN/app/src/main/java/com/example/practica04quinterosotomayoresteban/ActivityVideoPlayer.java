package com.example.practica04quinterosotomayoresteban;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class ActivityVideoPlayer extends AppCompatActivity {
    protected VideoView videoView;
    protected MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        videoView = findViewById(R.id.activity_my_video);
        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ninf);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();
        Toast.makeText(this, "Comienza video. .", Toast.LENGTH_LONG).show();
        videoView.setMediaController(mediaController);
    }
}