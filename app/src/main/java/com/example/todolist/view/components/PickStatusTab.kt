package com.example.todolist.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StatusButton(text: String, backgroundColor: Color, textColor: Color, onTapFunction: () -> Unit) {
    Box(
        modifier = Modifier
            .background(color = backgroundColor, shape = RoundedCornerShape(4.dp))
            .clickable {
                onTapFunction()
            }
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 5.dp, vertical = 4.dp)
        ) {
            Text(text, color = textColor)
        }

    }
}