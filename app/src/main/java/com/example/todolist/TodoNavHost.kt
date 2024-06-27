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
import com.example.todolist.model.enums.TodoScreen
import com.example.todolist.todo.TodoProto
import com.example.todolist.view.screen.NewTodoScreen
import com.example.todolist.view.screen.TodoInfoScreen
import com.example.todolist.view.screen.TodoListScreen

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
            route = "${TodoScreen.TodoInfo.name}/{uuid}/{title}/{duration}/{status}/{description}",
            arguments = listOf(
                navArgument("uuid") {type = NavType.StringType},
                navArgument("title") { type = NavType.StringType },
                navArgument("duration") { type = NavType.IntType },
                navArgument("status") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType }
            )
        ) {
                backStackEntry ->
            val uuid = backStackEntry.arguments?.getString("uuid") ?: ""
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val duration = backStackEntry.arguments?.getInt("duration") ?: 0
            val status = backStackEntry.arguments?.getString("status") ?: ""
            val description = backStackEntry.arguments?.getString("description") ?: ""

            val toDo = TodoProto.Todo.newBuilder()
                .setUuid(uuid)
                .setTitle(title)
                .setDescription(description)
                .setDuration(duration)
                .setStatus(status)
                .build()
            TodoInfoScreen(navController = navController, toDo = toDo) }
        composable(TodoScreen.NewTodo.name) { NewTodoScreen(navController = navController) }
    }
}