package com.example.todolist.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.model.Todo
import com.example.todolist.repository.TodoRepository
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
    val status: MutableState<String> = mutableStateOf("")

    fun saveToDo() {
        val newToDo = Todo(
            title = titleText.value,
            description = descriptionText.value,
            dueDate = dueDate.value,
            duration = duration.value,
            status = status.value
        )
        // 코루틴 작업을 위한 viewModelScope
        viewModelScope.launch {
            repository.saveTodo(newToDo)
        }
    }
}