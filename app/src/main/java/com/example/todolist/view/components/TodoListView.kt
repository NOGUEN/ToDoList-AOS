package com.example.todolist.view.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todolist.todo.TodoProto

@Composable
fun TodoListView(
    navController: NavController,
    todo: MutableList<TodoProto.Todo>,
    deleteFunction: (String) -> Unit
) {
    LazyColumn (
        modifier = Modifier.padding(horizontal = 10.dp)
    ) {
        items(todo) { todoItem ->
            TodoListCell(
                toDo = todoItem,
                navController = navController,
                deleteFunction = deleteFunction
            )
        }
    }
}