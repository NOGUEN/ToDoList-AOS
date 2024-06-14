package com.example.todolist

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
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
import com.example.todolist.viewmodel.NewTodoViewModel
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodoNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = TodoScreen.TodoList.name
) {
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    }

    CompositionLocalProvider(
        LocalViewModelStoreOwner provides viewModelStoreOwner
    ) {
        NewTodoScreen(navController = navController)
    }

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
        composable(TodoScreen.NewTodo.name) { backStackEntry ->
            val viewModel = hiltViewModel<NewTodoViewModel>()
            NewTodoScreen(navController = navController, viewModel = viewModel)
        }
    }

}