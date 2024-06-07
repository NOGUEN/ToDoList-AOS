package com.example.todolist.view.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InputTextField(
    text: String,
    onTextChanged: (String) -> Unit,
    height: Dp = 50.dp,
) {
    BasicTextField(
        value = text,
        onValueChange = onTextChanged,
        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Left,
            fontSize = 20.sp
        ),
        modifier = Modifier
            .background(color = Color.White)
            .height(height = height)
            .fillMaxWidth(),
        decorationBox = { innerTextField ->
            Box(modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 10.dp)
            ) {
                innerTextField()
            }
        }
    )
}