package com.example.todolist.view.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.todolist.todo.TodoProto
import com.example.todolist.view.theme.ToDoListTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoInfoScreen(
    navController: NavHostController,
    toDo: TodoProto.Todo,
) {
    ToDoListTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = toDo.title) },
                    navigationIcon = {
                        Box(modifier = Modifier.clickable {
                            navController.popBackStack()
                        })
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                )
            },
            content = {
                Box(modifier = Modifier.padding(it)) {
                    Column(modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                    ) {
                        Text(text = toDo.description)
                        Text(text = toDo.status)
                        Text(text = "${toDo.duration}")
                        Text(text = "${toDo.dueDate}")
                    }
                }
            }
        )
    }
}