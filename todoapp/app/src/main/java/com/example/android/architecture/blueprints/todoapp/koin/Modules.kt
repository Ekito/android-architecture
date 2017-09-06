package com.example.android.architecture.blueprints.todoapp.koin

import com.example.android.architecture.blueprints.todoapp.Injection
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import com.example.android.architecture.blueprints.todoapp.tasks.TasksFilterType
import com.example.android.architecture.blueprints.todoapp.tasks.TasksFragment
import com.example.android.architecture.blueprints.todoapp.tasks.TasksPresenter
import org.koin.android.AndroidModule
import org.koin.dsl.module.Module

/**
 * Created by arnaud on 06/09/2017.
 */

class TodoGlobalModule : AndroidModule() {
    override fun context() = declareContext {

    }
}

class TasksActivityModule : AndroidModule() {
    override fun context() = declareContext {
        scope { TasksActivity::class }
        provide { TasksFragment.newInstance() } bind {Task}
        provide { TasksPresenter(get(),get())}
    }
}