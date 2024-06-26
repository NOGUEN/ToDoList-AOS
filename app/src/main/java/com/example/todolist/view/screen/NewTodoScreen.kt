package com.example.todolist.view.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.todolist.view.components.InputTextField
import com.example.todolist.view.theme.ToDoListTheme
import com.example.todolist.viewmodel.NewTodoViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTodoScreen(
    navController: NavHostController,
    viewModel: NewTodoViewModel = hiltViewModel()
) {
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
                            Icons.AutoMirrored.Filled.ArrowBack,
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
                            text = viewModel.titleText.value,
                            onTextChanged = {
                                viewModel.titleText.value = it
                            }
                        )
                        Box(modifier = Modifier.height(height = 10.dp))
                        InputTextField(
                            text = viewModel.descriptionText.value,
                            onTextChanged = {
                                viewModel.descriptionText.value = it
                            },
                            height = 200.dp
                        )

                    }
                }
            },
            bottomBar = {
                Box(modifier = Modifier
                    .padding(
                        horizontal = 20.dp, vertical = 10.dp
                    )
                    .fillMaxWidth()
                    .height(height = 50.dp)
                    .clickable {
                        viewModel.addToDo()
                    }
                    .background(color = Color(0xFFD3E5FF), shape = RoundedCornerShape(12.dp))
                ) {
                    Text(modifier = Modifier.align(Alignment.Center),
                        text = "저장")
                }
            }
        )
    }
}