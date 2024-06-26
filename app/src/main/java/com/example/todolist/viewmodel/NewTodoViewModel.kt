package com.example.todolist.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.repository.TodoRepository
import com.example.todolist.todo.TodoProto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewTodoViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {
    val titleText: MutableState<String> = mutableStateOf("")
    val descriptionText: MutableState<String> = mutableStateOf("")
    val dueDate: MutableState<Long> = mutableStateOf(0L)
    val duration: MutableState<Int> = mutableStateOf(0)
    val status: MutableState<String> = mutableStateOf("hello")

    fun addToDo() {
        val newToDo = TodoProto.Todo.newBuilder()
            .setTitle(titleText.value)
            .setDescription(descriptionText.value)
            .setDueDate(dueDate.value)
            .setDuration(duration.value)
            .setStatus(status.value)
            .build()

        viewModelScope.launch {
            repository.addTodo(newToDo)
        }
    }
}