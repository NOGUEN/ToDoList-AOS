package com.example.todolist.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ToDoListCell(
    cornerRadius: Dp = 12.dp,
    backgroundColor: Color = Color.White,
    titleText: String,
    status: String,
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
            Text(text = titleText, fontSize = 16.sp)
            Text(text = status, fontSize = 14.sp)
        }
    }
}