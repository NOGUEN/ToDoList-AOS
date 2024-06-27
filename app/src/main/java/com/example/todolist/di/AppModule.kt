package com.example.todolist.di


import android.content.Context
import com.example.todolist.WorkManager
import com.example.todolist.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun provideNewTodoRepository(@ApplicationContext
                                 context: Context): TodoRepository {
        return TodoRepository(context = context)
    }

    @Provides
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager {
        return WorkManager(context)
    }
}