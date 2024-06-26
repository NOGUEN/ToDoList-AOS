package com.example.todolist.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.model.enums.Status
import com.example.todolist.repository.TodoRepository
import com.example.todolist.todo.TodoProto
import com.example.todolist.view.theme.DisabledDoneColor
import com.example.todolist.view.theme.DisabledOnGoingColor
import com.example.todolist.view.theme.DisabledReadyColor
import com.example.todolist.view.theme.DoneColor
import com.example.todolist.view.theme.OnGoingColor
import com.example.todolist.view.theme.ReadyColor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class NewTodoViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {
    val titleText: MutableState<String> = mutableStateOf("")
    val descriptionText: MutableState<String> = mutableStateOf("")
    val dueDate: MutableState<Long> = mutableStateOf(0L)
    val status: MutableState<String> = mutableStateOf("Ready")
    val tapped: MutableList<Boolean> = mutableListOf(true, false, false)
    val statusColor: MutableList<MutableState<Color>> = mutableListOf(mutableStateOf(ReadyColor), mutableStateOf(
        DisabledOnGoingColor), mutableStateOf(DisabledDoneColor))


    fun addToDo() {
        val newToDo = TodoProto.Todo.newBuilder()
            .setUuid(UUID.randomUUID().toString())
            .setTitle(titleText.value)
            .setDescription(descriptionText.value)
            .setDueDate(dueDate.value)
            .setStatus(status.value)
            .build()

        viewModelScope.launch {
            repository.addTodo(newToDo)
        }
    }

    fun tapStatus(index: Int) {
        for (idx in tapped.indices) {
            tapped[idx] = false
        }
        statusColor[0].value = DisabledReadyColor
        statusColor[1].value = DisabledOnGoingColor
        statusColor[2].value = DisabledDoneColor

        tapped[index] = true
        when (index) {
            0 -> {
                status.value = Status.Ready.name
                statusColor[0].value = ReadyColor
            }
            1 -> {
                status.value = Status.OnGoing.name
                statusColor[1].value = OnGoingColor
            }
            2 -> {
                status.value = Status.Done.name
                statusColor[2].value = DoneColor
            }
        }
    }
}