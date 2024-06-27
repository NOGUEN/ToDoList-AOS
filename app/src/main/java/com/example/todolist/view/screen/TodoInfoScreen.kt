package com.example.todolist.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.todolist.model.enums.Status
import com.example.todolist.todo.TodoProto
import com.example.todolist.view.components.StatusButton
import com.example.todolist.view.theme.BackgroundColor
import com.example.todolist.view.theme.Primary
import com.example.todolist.view.theme.SecondaryBackgroundColor
import com.example.todolist.view.theme.ToDoListTheme
import com.example.todolist.viewmodel.TodoInfoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoInfoScreen(
    navController: NavHostController,
    toDo: TodoProto.Todo,
    viewModel: TodoInfoViewModel = hiltViewModel()
) {
    viewModel.setStatus(toDo.status)

    ToDoListTheme {
        Scaffold(
            containerColor = BackgroundColor,
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = BackgroundColor,
                        titleContentColor = Primary,
                    ),
                    title = {
                        Text(text = toDo.title)
                    },
                    navigationIcon = {
                        Box(modifier = Modifier.clickable {
                            navController.popBackStack()
                        })
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            tint = Primary,
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
                        Text("설명", color = Color.White, fontSize = 20.sp)
                        Box(modifier = Modifier.height(10.dp))
                        Box(modifier = Modifier
                            .background(color = SecondaryBackgroundColor, shape = RoundedCornerShape(12.dp))
                            .fillMaxWidth()
                            .size(height = 150.dp, width = 10.dp)
                        ){
                            Box(
                                modifier = Modifier
                                    .padding(all = 10.dp)
                            ) {
                                var descriptionText = ""
                                if (toDo.description != "EmptyDescriptionTag")
                                    descriptionText = toDo.description

                                Text(text = descriptionText, color = Color.White)
                            }
                        }
                        Box(modifier = Modifier.height(height = 20.dp))
                        Text("상태", color = Color.White, fontSize = 20.sp)
                        Box(modifier = Modifier.height(10.dp))
                        Row() {
                            StatusButton(
                                text = Status.Ready.name,
                                backgroundColor = viewModel.statusColor[0].value,
                                textColor = Color.Black,
                                onTapFunction = {
                                    viewModel.tapStatus(0)
                                }
                            )
                            Box(modifier = Modifier.width(10.dp))
                            StatusButton(
                                text = Status.OnGoing.name,
                                backgroundColor = viewModel.statusColor[1].value,
                                textColor = Color.Black,
                                onTapFunction = {
                                    viewModel.tapStatus(1)
                                }
                            )
                            Box(modifier = Modifier.width(10.dp))
                            StatusButton(
                                text = Status.Done.name,
                                backgroundColor = viewModel.statusColor[2].value,
                                textColor = Color.Black,
                                onTapFunction = {
                                    viewModel.tapStatus(2)
                                }
                            )
                        }
                        Box(modifier = Modifier.height(height = 20.dp))
                        Text("설정된 타이머", color = Color.White, fontSize = 20.sp)
                        Box(modifier = Modifier.height(10.dp))
                    }
                }
            }
        )
    }
}