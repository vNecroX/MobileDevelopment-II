package com.example.practica03quinterosotomayoresteban;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public static final String tagOnNewToken = "NEW_TOKEN";
    public static final String tagOnMessageReceived = "TOMR";

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        Log.d(tagOnNewToken,"NEW TOKEN: " + token);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

        String from = message.getFrom();
        Log.d(tagOnMessageReceived, "FROM0: " + from);

        if(message.getNotification() != null)
            throwNotification(message.getNotification().getTitle(), message.getNotification().getBody());

        if(message.getData().size() > 0)
            Log.d(tagOnMessageReceived, "FROM1: " + message.getData());
    }

    public void throwNotification(String title, String body){
        /*
        CharSequence name = "channel";
        String description = "Channel for notification";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        */

        Log.d(tagOnMessageReceived, "Title: " + title + ", body: " + body);

        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, builder.build());

        /*
        NotificationChannel notificationChannel = new NotificationChannel("NOTIFICATION", name, importance);
        notificationChannel.setDescription(description);

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(notificationChannel);
        */
    }
}
