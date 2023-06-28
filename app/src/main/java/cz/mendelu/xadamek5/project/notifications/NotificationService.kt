package cz.mendelu.xadamek5.project.notifications

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import cz.mendelu.xadamek5.project.R
import cz.mendelu.xadamek5.project.ui.activities.MainActivity
import java.util.*

class NotificationService(private val context: Context) {
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(){
        val activityIntent = Intent(context, MainActivity::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.no_smoking)
            .setContentTitle("Odvykačka")
            .setContentText("Don`t forget to log today!")
            .setContentIntent(activityPendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(1, notification)
    }
/*
    private fun scheduleNotification() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val notificationIntent = Intent(this, MyNotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, 0)

        // Set the notification time to 16:00
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 16)
            set(Calendar.MINUTE, 0)
        }

        // Schedule the alarm to trigger at the specified time
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }


    private fun Context.bitmapFromResource(
        @DrawableRes resId: Int
    ) = BitmapFactory.decodeResource(
        resources,
        resId
    )
*/
    companion object {
        const val CHANNEL_ID = "odvykacka_channel"
    }


}