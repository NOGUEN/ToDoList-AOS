package com.example.todolist.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun StatusButton(text: String, backgroundColor: Color, textColor: Color, onTapFunction: () -> Unit) {
    Box(
        modifier = Modifier
            .background(color = backgroundColor)
            .clickable {
                onTapFunction()
            }
    ) {
        Text(text, color = textColor)
    }
}