package com.example.alarmclock;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class AlarmService extends Service {
    private Ringtone ringtone;
    private static final String CHANNEL_ID = "AlarmServiceChannel";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String alarmTitle = intent.getStringExtra("alarm_title");
        String ringtoneUriString = intent.getStringExtra("ringtone_uri");

        if (alarmTitle == null) alarmTitle = "Alarm";

        Uri ringtoneUri;
        if (ringtoneUriString != null) {
            ringtoneUri = Uri.parse(ringtoneUriString);
        } else {
            ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        }

        ringtone = RingtoneManager.getRingtone(this, ringtoneUri);
        if (ringtone != null) {
            ringtone.play();
        }

        Intent stopIntent = new Intent(this, StopAlarmReceiver.class);
        PendingIntent stopPendingIntent = PendingIntent.getBroadcast(this, 0, stopIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Alarm: " + alarmTitle)
                .setContentText("Ringing...")
                .setSmallIcon(android.R.drawable.ic_lock_idle_alarm)
                .addAction(android.R.drawable.ic_menu_close_clear_cancel, "Stop", stopPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .setFullScreenIntent(null, true)
                .build();

        startForeground(1, notification);

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        if (ringtone != null && ringtone.isPlaying()) {
            ringtone.stop();
        }
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Alarm Service Channel",
                    NotificationManager.IMPORTANCE_HIGH
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(serviceChannel);
            }
        }
    }
}