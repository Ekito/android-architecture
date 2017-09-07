package com.example.android.architecture.blueprints.todoapp.koin

import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
import com.example.android.architecture.blueprints.todoapp.data.source.local.TasksLocalDataSource
import com.example.android.architecture.blueprints.todoapp.data.source.remote.TasksRemoteDataSource
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import com.example.android.architecture.blueprints.todoapp.tasks.TasksContract
import com.example.android.architecture.blueprints.todoapp.tasks.TasksFragment
import com.example.android.architecture.blueprints.todoapp.tasks.TasksPresenter
import org.koin.android.AndroidModule

/**
 * Created by arnaud on 06/09/2017.
 */

class TasksActivityModule : AndroidModule() {
    override fun context() = declareContext {
        scope { TasksActivity::class }
        provide { TasksFragment.newInstance() } bind { TasksContract.View::class }
        provide { TasksPresenter(get(), get()) }
    }
}