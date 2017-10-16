package com.example.android.architecture.blueprints.todoapp

import com.example.android.architecture.blueprints.todoapp.data.source.TasksDataSource
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
import com.example.android.architecture.blueprints.todoapp.data.source.local.TasksLocalDataSource
import com.example.android.architecture.blueprints.todoapp.data.source.remote.TasksRemoteDataSource
import org.koin.android.AndroidModule

/**
 * Repository module
 */
class RepositoryModule : AndroidModule() {
    override fun context() = applicationContext {
        provide("remoteDataSource") { TasksRemoteDataSource() }
        provide("localDataSource") { TasksLocalDataSource(applicationContext) }

        provide { TasksRepository(get("remoteDataSource"), get("localDataSource")) } bind (TasksDataSource::class)
    }
}