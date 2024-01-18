package com.example.todolist.di

import android.content.Context
import com.example.todolist.data.TodoRepositoryImpl
import com.example.todolist.data.database.TodoDao
import com.example.todolist.data.database.TodoDatabase
import com.example.todolist.domain.TodoRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Singleton
    @Provides
    fun provideTodoRepository(dao: TodoDao): TodoRepository {
        return TodoRepositoryImpl(dao)
    }

    @Singleton
    @Provides
    fun provideDao(database: TodoDatabase): TodoDao {
        return database.getTodoDao()
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): TodoDatabase {
        return TodoDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideFirebase(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}