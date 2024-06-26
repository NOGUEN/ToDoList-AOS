package com.example.todolist.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TodoButton(
    height: Dp,
    color: Color,
    onTap: () -> Unit,

    ) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(height = height)
        .clickable {
            onTap()
        }
        .background(color = color, shape = RoundedCornerShape(12.dp)),
    ) {
        Text(modifier = Modifier.align(Alignment.Center),
            text = "저장")
    }
}