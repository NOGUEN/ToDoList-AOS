package com.example.todolist.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.WorkManager
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
    private val repository: TodoRepository,
    private val workManager: WorkManager
) : ViewModel() {
    val titleText: MutableState<String> = mutableStateOf("")
    val descriptionText: MutableState<String> = mutableStateOf("")
    val durationHour: MutableState<String> = mutableStateOf("0")
    val durationMinute: MutableState<String> = mutableStateOf("0")
    val status: MutableState<String> = mutableStateOf("Ready")
    val tapped: MutableList<Boolean> = mutableListOf(true, false, false)
    val statusColor: MutableList<MutableState<Color>> = mutableListOf(mutableStateOf(ReadyColor), mutableStateOf(
        DisabledOnGoingColor), mutableStateOf(DisabledDoneColor))
    var submitAvailability: MutableState<Boolean> = mutableStateOf(false)

    @RequiresApi(Build.VERSION_CODES.S)
    fun addToDo() {
        val uuid = UUID.randomUUID().toString()

        val newToDo = TodoProto.Todo.newBuilder()
            .setUuid(uuid)
            .setTitle(titleText.value)
            .setDescription(descriptionText.value)
            .setDuration(durationHour.value.toInt() * 60 + durationMinute.value.toInt())
            .setStatus(status.value)
            .build()

        val doneToDo = TodoProto.Todo.newBuilder()
            .setUuid(uuid)
            .setTitle(titleText.value)
            .setDescription(descriptionText.value)
            .setDuration(durationHour.value.toInt() * 60 + durationMinute.value.toInt())
            .setStatus(Status.Done.name)
            .build()

        viewModelScope.launch {
            repository.addTodo(newToDo)
            if (durationHour.value.toLong() * 60 + durationMinute.value.toLong() != 0L) {
                workManager.setAlarm(durationHour.value.toLong() * 60 + durationMinute.value.toLong())
                workManager.scheduleStatusChange(durationHour.value.toLong() * 60 + durationMinute.value.toLong()) {
                    repository.updateTodoStatus(uuid, doneToDo)
                }
            }
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