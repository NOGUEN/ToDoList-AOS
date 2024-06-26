package com.example.todolist.repository

import android.content.Context
import com.example.todolist.todo.TodoProto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import todoDataStore
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val context: Context
) {
    val todoDataStore = context.todoDataStore

    fun readTodos(): Flow<TodoProto.TodoList> {
        return todoDataStore.data
    }

    fun addTodo(todo: TodoProto.Todo) {
        runBlocking {
            todoDataStore.updateData { currentTodos ->
                currentTodos.toBuilder()
                    .addTodos(todo)
                    .build()
            }
        }
    }

    fun updateTodoStatus(index: Int, newStatus: String) {
        runBlocking {
            todoDataStore.updateData { currentTodos ->
                val todosBuilder = currentTodos.toBuilder()
                if (index >= 0 && index < todosBuilder.todosCount) {
                    val updatedTodo = todosBuilder.getTodos(index).toBuilder()
                        .setStatus(newStatus)
                        .build()
                    todosBuilder.setTodos(index, updatedTodo)
                }
                todosBuilder.build()
            }
        }
    }

    fun deleteTodoAtIndex(index: Int) {
        runBlocking {
            todoDataStore.updateData { currentTodos ->
                val todosBuilder = currentTodos.toBuilder()
                if (index >= 0 && index < todosBuilder.todosCount) {
                    todosBuilder.removeTodos(index)
                }
                todosBuilder.build()
            }
        }
    }

    fun getTodoList(): List<TodoProto.Todo> {
        return runBlocking {
            todoDataStore.data.first().todosList
        }
    }
}
