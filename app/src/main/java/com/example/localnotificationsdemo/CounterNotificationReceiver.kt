package com.example.localnotificationsdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

// needs to be added to the manifest
class CounterNotificationReceiver : BroadcastReceiver() {

    // receives the intent from the action clicked and sends a new one with the updated data
    override fun onReceive(context: Context, intent: Intent?) {
        val service = CounterNotificationService(context)
        service.showNotification(++Counter.value)
    }
}