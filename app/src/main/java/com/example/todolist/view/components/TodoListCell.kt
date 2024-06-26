package com.example.todolist.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todolist.model.enums.TodoScreen
import com.example.todolist.todo.TodoProto
import com.example.todolist.view.theme.SecondaryBackgroundColor

@Composable
fun TodoListCell(
    cornerRadius: Dp = 12.dp,
    toDo: TodoProto.Todo,
    navController: NavController,
) {
    Box(
        modifier = Modifier
            .padding(top = 10.dp)
            .clickable {
                navController.navigate(
                    "${TodoScreen.TodoInfo.name}/${toDo.uuid}/${toDo.title}/${toDo.description}/${toDo.dueDate}/${toDo.status}"
                )
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(SecondaryBackgroundColor, shape = RoundedCornerShape(cornerRadius))
        ) {
            Row() {
                Column(
                    Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
                ) {
                    Text(text = toDo.title, fontSize = 16.sp, color = Color.White)
                    Box(modifier = Modifier.size(width = 0.dp, height = 10.dp))
                    StatusTag(statusString = toDo.status)
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .size(
                            width = 30.dp,
                            height = 50.dp
                        )
                        .padding(
                            end = 10.dp,
                            top = 10.dp,
                        )
                ) {
                    DeleteMenuButton(
                        onDeleteFunction = {

                        }
                    )
                }

            }
        }
    }
}
