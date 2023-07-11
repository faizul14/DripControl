package com.faezolfp.dripcontrol.core.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.faezolfp.dripcontrol.R

class AlarmNotificationService : Service() {

    companion object {
        const val CHANNEL_ID = "AlarmChannelId"
        const val NOTIFICATION_ID = 1
        const val NOTIFICATION_ACTION_STOP = "com.example.alarmapp.STOP"
    }

    private lateinit var mediaPlayer: MediaPlayer

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.custom_sound)
        mediaPlayer.isLooping = true
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == NOTIFICATION_ACTION_STOP) {
            stopForeground(true)
            stopSelf()
        } else {
            startForeground(NOTIFICATION_ID, createNotification())
        }
        mediaPlayer.start()
        return START_NOT_STICKY
    }

    private fun createNotification(): Notification {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Alarm Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.setSound(
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM),
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
            )
            notificationManager.createNotificationChannel(channel)
        }

        val stopIntent = Intent(this, AlarmNotificationService::class.java)
            .setAction(NOTIFICATION_ACTION_STOP)
        val stopPendingIntent = PendingIntent.getService(
            this,
            0,
            stopIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Peringatan")
            .setContentText("Cairan infus pasien kurang dari 20%.")
            .setSmallIcon(R.drawable.baseline_notifications_24)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_REMINDER)
            .setAutoCancel(true)
            .setOngoing(true)
            .setSound(null)
            .addAction(R.drawable.baseline_notifications_24, "Stop", stopPendingIntent)
            .build()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}
