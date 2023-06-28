package cz.mendelu.xadamek5.project.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import cz.mendelu.xadamek5.project.R

class NotificationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // Create and display the notification
        val notificationTitle = "Daily Notification"
        val notificationText = "This is your daily notification at 16:00."

        val notification = NotificationCompat.Builder(context, NotificationService.CHANNEL_ID)
            .setSmallIcon(R.drawable.no_smoking)
            .setContentTitle(notificationTitle)
            .setContentText(notificationText)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        val notificationManager = NotificationManagerCompat.from(context)
        //notificationManager.notify(NOTIFICATION_ID, notification)

        Log.d("Notification", "Notification received and displayed.")
    }
}




