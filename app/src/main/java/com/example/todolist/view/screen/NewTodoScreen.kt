package com.example.todolist.view.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.todolist.model.enums.Status
import com.example.todolist.view.components.InputTextField
import com.example.todolist.view.components.StatusButton
import com.example.todolist.view.components.TodoButton
import com.example.todolist.view.theme.BackgroundColor
import com.example.todolist.view.theme.Primary
import com.example.todolist.view.theme.ToDoListTheme
import com.example.todolist.viewmodel.NewTodoViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTodoScreen(
    navController: NavHostController,
    viewModel: NewTodoViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current

    ToDoListTheme {
        Scaffold(
            modifier = Modifier.addFocusCleaner(focusManager),
            containerColor = BackgroundColor,
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = BackgroundColor,
                        titleContentColor = Primary,
                    ),
                    title = { Text(text = "NewToDoList") },
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
                            },
                            height = 60.dp,
                            hintText = "새 할 일 제목"
                        )
                        Box(modifier = Modifier.height(height = 10.dp))
                        InputTextField(
                            text = viewModel.descriptionText.value,
                            onTextChanged = {
                                viewModel.descriptionText.value = it
                            },
                            height = 200.dp,
                            hintText = "새 할 일 설명"
                        )
                        Box(modifier = Modifier.height(height = 10.dp))
                        Row() {
                            StatusButton(
                                text = Status.Ready.name,
                                backgroundColor = viewModel.statusColor[0].value,
                                textColor = Color.Black,
                                onTapFunction = {
                                    viewModel.tapStatus(0)
                                }
                            )
                            StatusButton(
                                text = Status.OnGoing.name,
                                backgroundColor = viewModel.statusColor[1].value,
                                textColor = Color.Black,
                                onTapFunction = {
                                    viewModel.tapStatus(1)
                                }
                            )
                            StatusButton(
                                text = Status.Done.name,
                                backgroundColor = viewModel.statusColor[2].value,
                                textColor = Color.Black,
                                onTapFunction = {
                                    viewModel.tapStatus(2)
                                }
                            )
                        }
                    }
                }
            },
            bottomBar = {
                Box(modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                ) {
                    TodoButton(
                        50.dp,
                        Primary,
                        onTap = {
                            viewModel.addToDo()
                        }
                    )
                }
            }
        )
    }
}

fun Modifier.addFocusCleaner(focusManager: FocusManager, doOnClear: () -> Unit = {}): Modifier {
    return this.pointerInput(Unit) {
        detectTapGestures(onTap = {
            doOnClear()
            focusManager.clearFocus()
        })
    }
}