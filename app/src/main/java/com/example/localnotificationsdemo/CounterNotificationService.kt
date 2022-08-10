package com.example.localnotificationsdemo

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

class CounterNotificationService(
    private val context: Context
) {
    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    companion object {
        const val COUNTER_CHANNEL_ID = "counter_channel"
    }

    fun showNotification(counter: Int) {
        // a pending intent is a wrapper to a normal intent that allows an outside framework
        // to interact with our app or a section of it, manage the content or re-direct to our app
        val activityIntent = Intent(context, MainActivity::class.java)

        val activityPendingIntent =
            PendingIntent.getActivity( // because we want an activity to be launched
                context,
                1,
                activityIntent,
                PendingIntent.FLAG_IMMUTABLE
            )

        val incrementIntent =
            PendingIntent.getBroadcast( // because we want a broadcast to receive it
                context,
                2,
                Intent(context, CounterNotificationReceiver::class.java), // our broadcast
                PendingIntent.FLAG_IMMUTABLE
            )

        val notification = NotificationCompat.Builder(context, COUNTER_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Increment counter")
            .setContentText("The count is $counter")
            .setContentIntent(activityPendingIntent)
            .addAction(
                R.drawable.ic_launcher_foreground,
                "Increment",
                incrementIntent
            )
            .build()

        notificationManager.notify(1, notification) // if updating should use same id
    }


}