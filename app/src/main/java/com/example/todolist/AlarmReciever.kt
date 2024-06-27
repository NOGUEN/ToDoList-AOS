package com.example.todolist
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "알람이 울립니다!", Toast.LENGTH_LONG).show()
        NotificationHelper.sendNotification(
            context,
            "Todo Alarm",
            "알람이 울립니다!"
        )
    }
}
