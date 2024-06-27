package com.example.todolist.repository

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import com.example.todolist.AlarmReceiver
import com.example.todolist.todo.TodoProto
import kotlinx.coroutines.flow.Flow
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
            setAlarm(1)
        }
    }

    fun clearToDoList() {
        runBlocking {
            context.todoDataStore.updateData {
                it.toBuilder().clear().build()
            }
        }
    }

    fun updateTodoStatus(index: Int, newStatus: String) {
        runBlocking {
            todoDataStore.updateData { currentTodos ->
                val todosBuilder = currentTodos.toBuilder()
                if (index >= 0 && index < todosBuilder.todosCount) {
                    val updatedTodo = todosBuilder.getTodos(index).toBuilder()
                        .setStatus(newStatus)
                        .build()
                    todosBuilder.setTodos(index, updatedTodo)
                }
                todosBuilder.build()
            }
        }
    }

    fun deleteTodoByUuid(uuid: String) {
        runBlocking {
            context.todoDataStore.updateData { toDoList ->
                val updatedToDoList = toDoList.toBuilder()
                val index = toDoList.todosList.indexOfFirst { it.uuid == uuid }
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

            // 분 단위를 밀리초로 변환
            val timeInMillis = minutes * 60 * 1000
            // 현재 시간에 입력된 시간을 더하여 알람 시간을 설정합니다.
            val triggerAtMillis = System.currentTimeMillis() + timeInMillis
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)
        } else {
            // 정확한 알람을 예약할 수 없는 경우 사용자에게 설정 화면으로 안내합니다.
            val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
            context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        }
    }
}
