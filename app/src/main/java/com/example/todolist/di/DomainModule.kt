package com.example.todolist.di

import com.example.todolist.domain.TodoRepository
import com.example.todolist.domain.useCases.DeleteFromDBUseCase
import com.example.todolist.domain.useCases.GetAllTodosUseCase
import com.example.todolist.domain.useCases.InsertToDBUseCase
import com.example.todolist.domain.useCases.UpdateDBUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideDeleteFromDBUseCase(todoRepository: TodoRepository) : DeleteFromDBUseCase{
        return DeleteFromDBUseCase(todoRepository)
    }

    @Singleton
    @Provides
    fun provideGetAllTodosUseCase(todoRepository: TodoRepository) : GetAllTodosUseCase {
        return GetAllTodosUseCase(todoRepository)
    }

    @Singleton
    @Provides
    fun provideInsertToDBUseCase(todoRepository: TodoRepository): InsertToDBUseCase {
        return InsertToDBUseCase(todoRepository)
    }

    @Singleton
    @Provides
    fun provideUpdateDBUseCase(todoRepository: TodoRepository): UpdateDBUseCase {
        return UpdateDBUseCase(todoRepository)
    }


}
