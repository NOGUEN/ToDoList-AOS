package com.example.todolist.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todolist.TodoProto
import com.example.todolist.model.Todo
import com.example.todolist.todoDataStore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlinx.coroutines.runBlocking

class TodoRepository @Inject constructor(
    private val context: Context,
) {

    private val _todoList = MutableLiveData<List<Todo>>()
    val todoList: LiveData<List<Todo>> = _todoList


    fun readToDoFlow(): Flow<TodoProto.ToDo> {
        return context.todoDataStore.data
    }

    fun saveToDo(toDo: TodoProto.ToDo) {
        runBlocking {
            context.todoDataStore.updateData { currentToDo ->
                currentToDo.toBuilder()
                    .setTitle(toDo.title)
                    .setDescription(toDo.description)
                    .setDueDate(toDo.dueDate)
                    .setDuration(toDo.duration)
                    .setStatus(toDo.status)
                    .build()
            }
        }
    }

    fun updateToDoStatus(newStatus: String) {
        runBlocking {
            context.todoDataStore.updateData { currentToDo ->
                currentToDo.toBuilder()
                    .setStatus(newStatus)
                    .build()
            }
        }
    }



    suspend fun deleteTodoAtIndex(index: Int) {

    }
}
