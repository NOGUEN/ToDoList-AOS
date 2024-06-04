@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.todolist.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.todolist.ui.components.ToDoListCell
import com.example.todolist.ui.theme.ToDoListTheme

@Composable
fun MainScreen() {
    ToDoListTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "ToDoList") }
                )
            },
            content = {
                Column(
                    modifier = Modifier.padding(it)
                ) {
                    ToDoListCell(modifier = Modifier.padding(it))
                }
            }
        )
    }
}
