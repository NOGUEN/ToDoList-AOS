package com.example.todolist.model

data class ToDo(
    val title : String,
    val description : String,
    val dueDate : Long,
    val duration : Int,
    val status : String,
)
