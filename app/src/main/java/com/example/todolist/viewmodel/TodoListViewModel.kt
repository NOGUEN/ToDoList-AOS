package com.example.todolist.viewmodel

import androidx.lifecycle.ViewModel
import com.example.todolist.TodoProto
import com.example.todolist.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {
    var todoList : Flow<TodoProto.ToDo> = repository.readToDoFlow()
}