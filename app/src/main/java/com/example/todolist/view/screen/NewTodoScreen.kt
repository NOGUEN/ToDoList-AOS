package com.example.todolist.view.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.todolist.view.components.InputTextField
import com.example.todolist.view.theme.ToDoListTheme

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTodoScreen(
    navController: NavHostController,
) {
    val text = remember {
        mutableStateOf("")
    }

    ToDoListTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "NewToDoList") },
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
            content = { it ->
                Box(modifier = Modifier
                    .padding(it)
                    .padding(horizontal = 20.dp)
                ) {
                    Column(

                    ) {
                        InputTextField(
                            text = text.value,
                            onTextChanged = {
                                text.value = it
                            }
                        )
                        Box(modifier = Modifier.height(height = 10.dp))
                        InputTextField(
                            text = text.value,
                            onTextChanged = {
                                text.value = it
                            },
                            height = 200.dp
                        )
                    }
                }
            }
        )
    }
}