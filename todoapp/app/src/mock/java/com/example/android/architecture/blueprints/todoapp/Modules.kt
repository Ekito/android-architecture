package com.example.android.architecture.blueprints.todoapp

import com.example.android.architecture.blueprints.todoapp.data.FakeTasksRemoteDataSource
import com.example.android.architecture.blueprints.todoapp.data.source.TasksDataSource
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
import com.example.android.architecture.blueprints.todoapp.data.source.local.TasksLocalDataSource
import org.koin.dsl.module.applicationContext

/**
 * Repository module
 */
val RepositoryModule = applicationContext {
    bean("remoteDataSource") { FakeTasksRemoteDataSource() }
    bean("localDataSource") { TasksLocalDataSource(get()) }
    bean { TasksRepository(get("remoteDataSource"), get("localDataSource")) } bind TasksDataSource::class
}