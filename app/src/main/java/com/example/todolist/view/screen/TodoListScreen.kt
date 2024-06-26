@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.todolist.view.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.todolist.model.enums.TodoScreen
import com.example.todolist.view.components.TodoListView
import com.example.todolist.view.theme.BackgroundColor
import com.example.todolist.view.theme.Primary
import com.example.todolist.view.theme.SecondaryBackgroundColor
import com.example.todolist.view.theme.ToDoListTheme
import com.example.todolist.viewmodel.TodoListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    navController: NavHostController,
    viewModel: TodoListViewModel = hiltViewModel()
) {
    ToDoListTheme {
        Scaffold(
            containerColor = BackgroundColor,
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = BackgroundColor,
                        titleContentColor = Primary,
                    ),
                    title = { Text(text = "${viewModel.todoList.size}") }
                )
            },
            content = {
                Box(modifier = Modifier.padding(it)) {
                    TodoListView(navController, viewModel.todoList) {
                        viewModel.deleteTodoByUuid(it)
                    }
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    containerColor = Primary ,
                    onClick = {
                        navController.navigate(TodoScreen.NewTodo.name)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        tint = SecondaryBackgroundColor,
                        contentDescription = null
                    )
                }
            }
        )
    }
}
