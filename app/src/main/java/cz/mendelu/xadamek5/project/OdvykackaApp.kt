package cz.mendelu.xadamek5.project

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import cz.mendelu.xadamek5.project.di.*
import cz.mendelu.xadamek5.project.notifications.NotificationService
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class OdvykackaApp : Application() {

    override fun onCreate() {
        super.onCreate()
        //notifikace
        //createNotificationChannel()
        appContext = applicationContext
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@OdvykackaApp)
            modules(listOf(
                viewModelModule,
                repositoryModule,
                daoModule,
                databaseModule,
                dataStoreModule
            ))

        }

    }

    //notifikace
    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                NotificationService.CHANNEL_ID,
                "odvykacka",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "Used to remember user to log today"

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        lateinit var appContext: Context
            private set
    }
}