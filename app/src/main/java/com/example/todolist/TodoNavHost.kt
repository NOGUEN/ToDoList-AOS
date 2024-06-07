package com.example.todolist

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todolist.model.Todo
import com.example.todolist.model.enums.TodoScreen
import com.example.todolist.view.screen.NewTodoScreen
import com.example.todolist.view.screen.TodoInfoScreen
import com.example.todolist.view.screen.TodoListScreen
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodoNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = TodoScreen.TodoList.name
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(TodoScreen.TodoList.name) { TodoListScreen(navController = navController) }
        composable(
            route = "${TodoScreen.TodoInfo.name}/{title}/{description}/{dueDate}/{duration}/{status}",
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
            val toDo = Todo(
                title = title ?: "",
                description = description ?: "",
                dueDate = Date(dueDate ?: 0L).time,
                duration = duration ?: 0,
                status = status ?: ""
            )
            TodoInfoScreen(navController = navController, toDo = toDo) }
        composable(TodoScreen.NewTodo.name) { NewTodoScreen(navController = navController) }
    }
}