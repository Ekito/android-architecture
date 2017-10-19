package com.example.android.architecture.blueprints.todoapp.di

import com.example.android.architecture.blueprints.todoapp.RepositoryModule
import com.example.android.architecture.blueprints.todoapp.addedittask.AddEditTaskActivity
import com.example.android.architecture.blueprints.todoapp.addedittask.AddEditTaskContract
import com.example.android.architecture.blueprints.todoapp.addedittask.AddEditTaskFragment
import com.example.android.architecture.blueprints.todoapp.addedittask.AddEditTaskPresenter
import com.example.android.architecture.blueprints.todoapp.di.Context.AddEditTask
import com.example.android.architecture.blueprints.todoapp.di.Context.Statistics
import com.example.android.architecture.blueprints.todoapp.di.Context.TaskDetail
import com.example.android.architecture.blueprints.todoapp.di.Context.Tasks
import com.example.android.architecture.blueprints.todoapp.statistics.StatisticsContract
import com.example.android.architecture.blueprints.todoapp.statistics.StatisticsFragment
import com.example.android.architecture.blueprints.todoapp.statistics.StatisticsPresenter
import com.example.android.architecture.blueprints.todoapp.taskdetail.TaskDetailActivity
import com.example.android.architecture.blueprints.todoapp.taskdetail.TaskDetailContract
import com.example.android.architecture.blueprints.todoapp.taskdetail.TaskDetailFragment
import com.example.android.architecture.blueprints.todoapp.taskdetail.TaskDetailPresenter
import com.example.android.architecture.blueprints.todoapp.tasks.TasksContract
import com.example.android.architecture.blueprints.todoapp.tasks.TasksFragment
import com.example.android.architecture.blueprints.todoapp.tasks.TasksPresenter
import org.koin.android.module.AndroidModule

/**
 * Module list
 */
fun moduleList() = listOf(RepositoryModule(), TodoAppModule())

/**
 * Koin module
 */
class TodoAppModule : AndroidModule() {
    override fun context() = applicationContext {
        context(Tasks) {
            provide { TasksFragment() }
            provide { TasksPresenter(get()) } bind TasksContract.Presenter::class
        }

        context(TaskDetail) {
            provide { TaskDetailFragment() }
            provide { TaskDetailPresenter(getProperty(TaskDetailActivity.EXTRA_TASK_ID), get()) } bind TaskDetailContract.Presenter::class
        }

        context(Statistics) {
            provide { StatisticsFragment() }
            provide { StatisticsPresenter(get()) } bind StatisticsContract.Presenter::class
        }

        context(AddEditTask) {
            provide { AddEditTaskFragment() }
            provide { AddEditTaskPresenter(getProperty(AddEditTaskFragment.ARGUMENT_EDIT_TASK_ID), get(), getProperty(AddEditTaskActivity.SHOULD_LOAD_DATA_FROM_REPO_KEY)) } bind AddEditTaskContract.Presenter::class
        }
    }
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
