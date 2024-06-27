package com.example.todolist
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "작업 완료!", Toast.LENGTH_LONG).show()
        NotificationHelper.sendNotification(
            context,
            "작업 완료 알람",
            "작업 완료!"
        )
    }
}
