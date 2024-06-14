package com.example.todolist.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewTodoViewModel @Inject constructor() : ViewModel() {
    val titleText: MutableState<String> = mutableStateOf("")
    val descriptionText: MutableState<String> = mutableStateOf("")
}