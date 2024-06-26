package com.example.todolist.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todolist.view.theme.BackgroundColor
import com.example.todolist.view.theme.DarkBackgroundColor
import com.example.todolist.view.theme.SecondaryBackgroundColor

@Composable
fun DeleteMenuButton(onDeleteFunction: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    Column {
        Box(
            modifier = Modifier
                .clickable {
                    expanded = true
                }
        ) {
            Icon(imageVector = Icons.Filled.MoreVert,
                tint = Color.White,
                contentDescription = "")
        }
        DropdownMenu(
            modifier = Modifier
                .background(color = BackgroundColor),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = {Text(text = "삭제", color = Color.White,)},
                onClick = {
                    expanded = false
                    showDialog = true
                }
            )
        }
        if (showDialog) {
            AlertDialog(
                containerColor = DarkBackgroundColor,
                shape = RoundedCornerShape(12.dp),
                onDismissRequest = { showDialog = false },
                title = {
                    Text(text = "정말로 삭제하시겠습니까?", color = Color.White)
                },
                text = {
                    Text(text = "삭제된 할 일은 복구되지 않습니다.", color = Color.White)
                },
                confirmButton = {
                    dialogButton(onTapFunc = {
                        showDialog = false
                        onDeleteFunction() },
                        text = "삭제", color = Color.Red)
                },
                dismissButton = {
                    dialogButton(onTapFunc = { showDialog = false }, text = "취소", color = Color.White)
                }
            )
        }
    }
}


@Composable
fun dialogButton(onTapFunc: () -> Unit, text: String, color: Color) {
    Box(
        modifier = Modifier
            .clickable {
                onTapFunc()
            }
            .background(color = SecondaryBackgroundColor,
                shape = RoundedCornerShape(8.dp),

                )
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Text(text, color = color)
        }
    }
}