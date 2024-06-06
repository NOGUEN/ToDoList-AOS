package com.example.todolist.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.todolist.ui.theme.ToDoListTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoInfoScreen(
    navController: NavHostController,
) {
    ToDoListTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {Text(text = "ToDoList")}
                )
            },
            content = {
                Box(modifier = Modifier.padding(it)) {
                    Text(text = "hello")
                }
            }
        )
    }
}