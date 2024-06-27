package com.example.todolist.view.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp
import com.example.todolist.view.theme.Primary
import com.example.todolist.view.theme.SecondaryBackgroundColor

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InputTextField(
    text: String,
    onTextChanged: (String) -> Unit,
    height: Dp = 50.dp,
    hintText: String
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth()
            .height(height = height),
        value = text,
        onValueChange = onTextChanged,
        shape = RoundedCornerShape(12.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = SecondaryBackgroundColor,
            textColor = Color.White,
            focusedIndicatorColor = Primary,
            cursorColor = Primary,
        ),
        placeholder = {Text(hintText, color = Color.Gray)},
    )
}