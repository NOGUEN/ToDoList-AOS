package com.example.todolist

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todolist.model.ToDo
import com.example.todolist.model.enums.ToDoScreen
import com.example.todolist.ui.screen.NewToDoScreen
import com.example.todolist.ui.screen.ToDoInfoScreen
import com.example.todolist.ui.screen.ToDoListScreen
import java.util.Date

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
        composable(
            route = "${ToDoScreen.ToDoInfo.name}/{title}/{description}/{dueDate}/{duration}/{status}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType },
                navArgument("dueDate") { type = NavType.LongType },
                navArgument("duration") { type = NavType.IntType },
                navArgument("status") { type = NavType.StringType }
            )
        ) {
                backStackEntry ->
            val title = backStackEntry.arguments?.getString("title")
            val description = backStackEntry.arguments?.getString("description")
            val dueDate = backStackEntry.arguments?.getLong("dueDate")
            val duration = backStackEntry.arguments?.getInt("duration")
            val status = backStackEntry.arguments?.getString("status")
            val toDo = ToDo(
                title = title ?: "",
                description = description ?: "",
                dueDate = Date(dueDate ?: 0L).time,
                duration = duration ?: 0,
                status = status ?: ""
            )
            ToDoInfoScreen(navController = navController, toDo = toDo) }
        composable(ToDoScreen.NewToDo.name) { NewToDoScreen(navController = navController) }
    }
}