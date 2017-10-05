package com.example.android.architecture.blueprints.todoapp

import com.example.android.architecture.blueprints.todoapp.data.FakeTasksRemoteDataSource
import com.example.android.architecture.blueprints.todoapp.data.source.TasksDataSource
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
import com.example.android.architecture.blueprints.todoapp.data.source.local.TasksLocalDataSource
import org.koin.android.AndroidModule

/**
 * Created by arnaud on 06/09/2017.
 */
class RepositoryModule : AndroidModule() {
    override fun context() = declareContext {
        provide("remoteDataSource") { FakeTasksRemoteDataSource() }
        provide("localDataSource") { TasksLocalDataSource(applicationContext) }

        provide { TasksRepository(get("remoteDataSource"), get("localDataSource")) } bind (TasksDataSource::class)
    }
}