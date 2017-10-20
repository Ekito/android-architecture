package com.example.android.architecture.blueprints.todoapp

import com.example.android.architecture.blueprints.todoapp.data.FakeTasksRemoteDataSource
import com.example.android.architecture.blueprints.todoapp.data.source.TasksDataSource
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
import com.example.android.architecture.blueprints.todoapp.data.source.local.TasksLocalDataSource
import org.koin.android.module.AndroidModule

/**
 * Repository module
 */
class RepositoryModule : AndroidModule() {
    override fun context() = applicationContext {
        provide("remoteDataSource") { FakeTasksRemoteDataSource() }
        provide("localDataSource") { TasksLocalDataSource(get()) }

        provide { TasksRepository(get("remoteDataSource"), get("localDataSource")) } bind (TasksDataSource::class)
    }
}