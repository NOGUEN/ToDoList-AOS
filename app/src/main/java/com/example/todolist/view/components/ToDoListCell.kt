package com.example.todolist.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todolist.model.ToDo
import com.example.todolist.model.enums.Status
import com.example.todolist.model.enums.ToDoScreen

@Composable
fun ToDoListCell(
    cornerRadius: Dp = 12.dp,
    backgroundColor: Color = Color.White,
    toDo: ToDo,
    navController: NavController,
) {
    Box(
        modifier = Modifier
            .padding(top = 10.dp)
            .clickable {
                navController.navigate(
                    "${ToDoScreen.ToDoInfo.name}/${toDo.title}/${toDo.description}/${toDo.dueDate}/${toDo.duration}/${toDo.status}"
                )
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(4.dp, shape = RoundedCornerShape(cornerRadius))
                .background(backgroundColor)
        ) {
            Column(
                Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
            ) {
                Text(text = toDo.title, fontSize = 16.sp)
                Box(modifier = Modifier.size(width = 0.dp, height = 10.dp))
                StatusTag(statusString = toDo.status)
            }
        }
    }
}

@Composable
fun StatusTag(
    statusString: String,
) {
    var backgroundColor : Color = Color.Gray

    when (statusString) {
        Status.Ready.name -> backgroundColor = Color(0xFFD3E5FF)
        Status.OnGoing.name -> backgroundColor = Color.Yellow
        Status.Done.name -> backgroundColor = Color.Green
    }
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(4.dp))
            .background(color = backgroundColor)
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 5.dp, vertical = 2.dp)
        ) {
            Text(text = statusString)
        }
    }
}