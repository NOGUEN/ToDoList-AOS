package com.example.todolist.view.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todolist.model.ToDo
import com.example.todolist.model.enums.Status
import java.util.Date

@Composable
fun ToDoListView(
    navController: NavController
) {
    LazyColumn (
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        items(count = 20) {
            ToDoListCell(
                toDo = ToDo(
                    title = "고라니 밥주기",
                    description = "고라니 밥을 줍시당",
                    dueDate = Date(10).time,
                    duration = 100,
                    status = Status.Ready.name
                ),
                navController = navController)
        }
    }
}