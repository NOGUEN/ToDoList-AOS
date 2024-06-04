package com.example.todolist.model

import java.util.Date

data class ToDo(
    val title : String,
    val description : String,
    val dueDate : Date,
    val duration : Int,
    val status : String,
)
