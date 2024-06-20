package com.example.todolist.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todolist.model.Todo
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val context: Context,
) {

    private val _todoList = MutableLiveData<List<Todo>>()
    val todoList: LiveData<List<Todo>> = _todoList

    init {
        loadTodoList()
    }

    private fun loadTodoList() {

    }

    suspend fun saveTodo(todo: Todo) {

    }

    suspend fun deleteTodoAtIndex(index: Int) {

    }
}
