package com.faezolfp.dripcontrol.core.service

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.faezolfp.dripcontrol.R
import com.faezolfp.dripcontrol.presentation.tracking.TrackingScreenActivity

class NotificationBroadcastReceiver : BroadcastReceiver() {

    private companion object {
        const val CHANNEL_ID = "MyChannelId"
        const val NOTIFICATION_ID = 1
    }

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action

        if (action == "ACTION_SHOW_NOTIFICATION") {
            // Munculkan notifikasi dengan suara berulang-ulang
            showNotification(context)
        } else if (action == "ACTION_CANCEL_NOTIFICATION") {
            // Batalkan notifikasi
            cancelNotification(context)
        } else if (action == "ACTION_OPEN_ACTIVITY") {
            // Buka Activity tujuan saat notifikasi diklik
            openActivity(context)
            // Batalkan notifikasi
            cancelNotification(context)
        }
    }

    private fun showNotification(context: Context) {
        val intent = Intent(context, NotificationBroadcastReceiver::class.java)
        intent.action = "ACTION_OPEN_ACTIVITY"
        val openActivityIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

        val intent2 = Intent(context, NotificationBroadcastReceiver::class.java)
        intent2.action = "ACTION_CANCEL_NOTIFICATION"
        val CLOSE = PendingIntent.getBroadcast(context, 0, intent2, 0)
        // Membuat channel notifikasi (hanya diperlukan untuk API level 26+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "My Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.setSound(
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION),
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build()
            )

            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        // Membuat builder notifikasi
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_notifications_24)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    context.resources,
                    R.drawable.baseline_notification_important_24
                )
            )
            .setContentTitle("Judul Notifikasi")
            .setContentText("Isi Notifikasi")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true) // Menandai notifikasi untuk dibatalkan saat diklik
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
//            .setVibration(longArrayOf(0L)) // Menonaktifkan getar notifikasi
            .addAction(R.drawable.baseline_notifications_24, "buka", openActivityIntent )
            .addAction(R.drawable.baseline_add_24, "buTUTUPka", CLOSE )

        // Menampilkan notifikasi
        val notificationManagerCompat = NotificationManagerCompat.from(context)
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build())
    }

    private fun cancelNotification(context: Context) {
        val notificationManagerCompat = NotificationManagerCompat.from(context)
        notificationManagerCompat.cancel(NOTIFICATION_ID)
    }

    private fun openActivity(context: Context) {
        val intent = Intent(context, TrackingScreenActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}
