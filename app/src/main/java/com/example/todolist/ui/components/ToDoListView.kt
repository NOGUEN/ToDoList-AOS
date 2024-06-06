package com.example.todolist.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ToDoListView(
    navController: NavController
) {
    LazyColumn (
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        items(count = 20) {
            ToDoListCell(titleText = "", status = "", navController = navController)
        }
    }
}