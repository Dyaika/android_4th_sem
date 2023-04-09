package com.example.myapp6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_POST_NOTIFICATIONS_REQUEST_CODE = 105;
    private static int NOTIFY_ID = 1;
    private final Context context = this;
    private Button button;
    private static final String CHANNEL_ID = "CHANNEL_1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_background) // Устанавливаем иконку
                        .setContentTitle("Вы вызвали уведомления") // Устанавливаем заголовок
                        .setContentText("Значит они у вас как минимум ращрешены") // Устанавливаем текст
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT); // Устанавливаем приоритет
                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
                notificationManagerCompat.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "first_channel", NotificationManager.IMPORTANCE_DEFAULT));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{android.Manifest.permission.POST_NOTIFICATIONS},
                            PERMISSION_POST_NOTIFICATIONS_REQUEST_CODE);
                }
                notificationManagerCompat.notify(NOTIFY_ID, builder.build());
                NOTIFY_ID++;
            }
        });
    }
}