package com.example.todolist.view.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist.view.theme.Primary
import com.example.todolist.view.theme.SecondaryBackgroundColor

@Composable
fun NumberTextField(
    text: String,
    onTextChanged: (String) -> Unit,
    height: Dp = 50.dp,
    hintText: String,
    range: IntRange,
) {
    androidx.compose.material.OutlinedTextField(
        modifier = Modifier
            .width(width = 70.dp)
            .height(height = height),
        value = text,
        textStyle = TextStyle(fontSize = 30.sp, textAlign = TextAlign.Center),
        onValueChange = { newValue ->
            if (newValue.isEmpty()) {
                onTextChanged(newValue)
            } else {
                newValue.toIntOrNull()?.let {
                    if (it in range) {
                        onTextChanged(newValue)
                    }
                }
            }
        },
        label = { Text(hintText, color = Color.White, textAlign = TextAlign.Center) },
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = SecondaryBackgroundColor,
            textColor = Color.White,
            focusedIndicatorColor = Primary,
            cursorColor = Primary,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
    )
}
