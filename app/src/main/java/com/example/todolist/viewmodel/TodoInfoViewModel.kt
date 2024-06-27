package com.example.todolist.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.todolist.model.enums.Status
import com.example.todolist.repository.TodoRepository
import com.example.todolist.view.theme.DisabledDoneColor
import com.example.todolist.view.theme.DisabledOnGoingColor
import com.example.todolist.view.theme.DisabledReadyColor
import com.example.todolist.view.theme.DoneColor
import com.example.todolist.view.theme.OnGoingColor
import com.example.todolist.view.theme.ReadyColor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoInfoViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {
    val status: MutableState<String> = mutableStateOf("Ready")
    val tapped: MutableList<Boolean> = mutableListOf(true, false, false)
    val statusColor: MutableList<MutableState<Color>> = mutableListOf(
        mutableStateOf(ReadyColor), mutableStateOf(
        DisabledOnGoingColor
        ), mutableStateOf(DisabledDoneColor)
    )

    fun setStatus(toDoStatus: String) {
        for (idx in tapped.indices) {
            tapped[idx] = false
        }
        statusColor[0].value = DisabledReadyColor
        statusColor[1].value = DisabledOnGoingColor
        statusColor[2].value = DisabledDoneColor

        status.value = toDoStatus
        when (toDoStatus) {
            Status.Ready.name -> {
                tapped[0] = true
                statusColor[0].value = ReadyColor
            }
            Status.OnGoing.name -> {
                tapped[1] = true
                statusColor[1].value = OnGoingColor

            }
            Status.Done.name -> {
                tapped[2] = true
                statusColor[2].value = DoneColor
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