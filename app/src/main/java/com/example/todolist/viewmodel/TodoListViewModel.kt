package com.example.todolist.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.repository.TodoRepository
import com.example.todolist.todo.TodoProto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {
    val todoList = mutableStateListOf<TodoProto.Todo>()

    init {
        viewModelScope.launch {
            repository.readTodos().collect { todos ->
                todoList.clear()
                todoList.addAll(todos.todosList)
            }
        }
    }
}