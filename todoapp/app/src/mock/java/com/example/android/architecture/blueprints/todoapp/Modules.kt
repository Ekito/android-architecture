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
        //TODO bind multi components for TasksDataSource
        provide { TasksRepository(FakeTasksRemoteDataSource(), TasksLocalDataSource(applicationContext)) } bind { TasksDataSource::class }
    }
}