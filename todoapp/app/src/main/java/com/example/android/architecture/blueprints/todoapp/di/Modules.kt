package com.example.android.architecture.blueprints.todoapp.di

import com.example.android.architecture.blueprints.todoapp.RepositoryModule
import com.example.android.architecture.blueprints.todoapp.addedittask.AddEditTaskContract
import com.example.android.architecture.blueprints.todoapp.addedittask.AddEditTaskFragment
import com.example.android.architecture.blueprints.todoapp.addedittask.AddEditTaskPresenter
import com.example.android.architecture.blueprints.todoapp.di.Context.AddEditTask
import com.example.android.architecture.blueprints.todoapp.di.Context.Statistics
import com.example.android.architecture.blueprints.todoapp.di.Context.TaskDetail
import com.example.android.architecture.blueprints.todoapp.di.Context.Tasks
import com.example.android.architecture.blueprints.todoapp.di.Properties.ARGUMENT_EDIT_TASK_ID
import com.example.android.architecture.blueprints.todoapp.di.Properties.CURRENT_FILTERING_KEY
import com.example.android.architecture.blueprints.todoapp.di.Properties.EXTRA_TASK_ID
import com.example.android.architecture.blueprints.todoapp.di.Properties.SHOULD_LOAD_DATA_FROM_REPO_KEY
import com.example.android.architecture.blueprints.todoapp.statistics.StatisticsContract
import com.example.android.architecture.blueprints.todoapp.statistics.StatisticsFragment
import com.example.android.architecture.blueprints.todoapp.statistics.StatisticsPresenter
import com.example.android.architecture.blueprints.todoapp.taskdetail.TaskDetailContract
import com.example.android.architecture.blueprints.todoapp.taskdetail.TaskDetailFragment
import com.example.android.architecture.blueprints.todoapp.taskdetail.TaskDetailPresenter
import com.example.android.architecture.blueprints.todoapp.tasks.TasksContract
import com.example.android.architecture.blueprints.todoapp.tasks.TasksFilterType
import com.example.android.architecture.blueprints.todoapp.tasks.TasksFragment
import com.example.android.architecture.blueprints.todoapp.tasks.TasksPresenter
import org.koin.dsl.module.applicationContext

/**
 * Koin main module
 */
val TodoAppModule = applicationContext {
    context(Tasks) {
        provide { TasksFragment() }
        provide { TasksPresenter(getProperty(CURRENT_FILTERING_KEY, TasksFilterType.ALL_TASKS), get()) } bind TasksContract.Presenter::class
    }

    context(TaskDetail) {
        provide { TaskDetailFragment() }
        provide { TaskDetailPresenter(getProperty(EXTRA_TASK_ID), get()) } bind TaskDetailContract.Presenter::class
    }

    context(Statistics) {
        provide { StatisticsFragment() }
        provide { StatisticsPresenter(get()) } bind StatisticsContract.Presenter::class
    }

    context(AddEditTask) {
        provide { AddEditTaskFragment() }
        provide { AddEditTaskPresenter(getProperty(ARGUMENT_EDIT_TASK_ID), get(), getProperty(SHOULD_LOAD_DATA_FROM_REPO_KEY, true)) } bind AddEditTaskContract.Presenter::class
    }
}


/**
 * Module list
 */
val todoAppModules = listOf(RepositoryModule, TodoAppModule)

object Properties {
    const val CURRENT_FILTERING_KEY = "CURRENT_FILTERING_KEY"
    const val EXTRA_TASK_ID = "TASK_ID"
    const val ARGUMENT_EDIT_TASK_ID = "EDIT_TASK_ID"
    const val SHOULD_LOAD_DATA_FROM_REPO_KEY = "SHOULD_LOAD_DATA_FROM_REPO_KEY"
}


/**
 * Module constants
 */
object Context {
    val Tasks = "Tasks"
    val TaskDetail = "TaskDetail"
    val Statistics = "Statistics"
    val AddEditTask = "AddEditTask"
}
