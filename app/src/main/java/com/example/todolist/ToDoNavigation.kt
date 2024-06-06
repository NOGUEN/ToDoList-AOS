package com.example.todolist

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todolist.model.ToDoScreen
import com.example.todolist.ui.screen.ToDoInfoScreen
import com.example.todolist.ui.screen.ToDoListScreen

@Composable
fun ToDoNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = ToDoScreen.ToDoList.name
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(ToDoScreen.ToDoList.name) { ToDoListScreen(navController = navController) }
        composable(ToDoScreen.ToDoInfo.name) { ToDoInfoScreen(navController = navController) }
    }
}