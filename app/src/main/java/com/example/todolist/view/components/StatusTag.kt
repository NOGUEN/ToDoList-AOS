package com.example.todolist.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todolist.model.enums.Status
import com.example.todolist.view.theme.DoneColor
import com.example.todolist.view.theme.OnGoingColor
import com.example.todolist.view.theme.ReadyColor


@Composable
fun StatusTag(
    statusString: String,
) {
    var backgroundColor : Color = Color.Gray

    when (statusString) {
        Status.Ready.name -> backgroundColor = ReadyColor
        Status.OnGoing.name -> backgroundColor = OnGoingColor
        Status.Done.name -> backgroundColor = DoneColor
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