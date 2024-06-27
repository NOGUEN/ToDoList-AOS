package com.example.todolist.repository

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.todolist.AlarmReceiver
import com.example.todolist.todo.TodoProto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import todoDataStore
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val context: Context
) {
    val todoDataStore = context.todoDataStore

    fun readTodos(): Flow<TodoProto.TodoList> {
        return todoDataStore.data
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun addTodo(todo: TodoProto.Todo) {
        runBlocking {
            todoDataStore.updateData { currentTodos ->
                currentTodos.toBuilder()
                    .addTodos(todo)
                    .build()
            }
        }
    }

    fun clearToDoList() {
        runBlocking {
            context.todoDataStore.updateData {
                it.toBuilder().clear().build()
            }
        }
    }

    fun updateTodoStatus(uuid: String, todo: TodoProto.Todo) {
        runBlocking {
            context.todoDataStore.updateData { toDoList ->
                val updatedToDoList = toDoList.toBuilder()
                val index = toDoList.todosList.indexOfFirst { it.uuid == uuid }
                if (index != -1) {
                    updatedToDoList.setTodos(index, todo)
                }
                updatedToDoList.build()
            }
        }
    }

    fun getTodoByUuid(uuid: String): TodoProto.Todo? = runBlocking {
        var todo: TodoProto.Todo? = null

        context.todoDataStore.updateData { toDoList ->
            val updatedToDoList = toDoList.toBuilder()
            val index = toDoList.todosList.indexOfFirst { it.uuid == uuid }
            if (index != -1) {
                todo = updatedToDoList.getTodos(index)
            }
            updatedToDoList.build()
        }

        return@runBlocking todo
    }

    fun deleteTodoByUuid(uuid: String) {
        getTodoByUuid(uuid)
        runBlocking {
            context.todoDataStore.updateData { toDoList ->
                val updatedToDoList = toDoList.toBuilder()
                val index = toDoList.todosList.indexOfFirst { it.uuid == uuid }
                Log.d("ffffff", "${index}")
                if (index != -1) {
                    updatedToDoList.removeTodos(index)
                }
                updatedToDoList.build()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun setAlarm(minutes: Long) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        if (alarmManager.canScheduleExactAlarms()) {
            val intent = Intent(context, AlarmReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            val timeInMillis = minutes * 60 * 1000
            val triggerAtMillis = System.currentTimeMillis() + timeInMillis
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)
        } else {
            val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
            context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }
    }
}
