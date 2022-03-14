package net.matrixhome.notificationapp.ui

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import net.matrixhome.notificationapp.*

class AppNotification(private val context: Context) {
    fun build(message: String){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val mChannel = NotificationChannel(
                CHANNEL_ID
                , context.resources.getString(R.string.app_notification)
                , NotificationManager.IMPORTANCE_DEFAULT)
            mChannel.description = context.resources.getString(R.string.notification_description)
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
        val intent = Intent(context.applicationContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("fr_number", message)
        }

        val pendongIntent: PendingIntent = PendingIntent.getActivity(context
            , NOTIFICATION_REQUEST_CODE
            , intent
            , PendingIntent.FLAG_UPDATE_CURRENT)
        val builder = NotificationCompat.Builder(context.applicationContext, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle(context.getString(R.string.notification_titile))
            .setContentText("${context.getString(R.string.notification_text)} $message")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendongIntent)
            .setAutoCancel(true)
        with(NotificationManagerCompat.from(context.applicationContext)){
            notify(NOTIFICATION_ID, builder.build())
        }
    }
}