package com.example.android.architecture.blueprints.todoapp.di

import com.example.android.architecture.blueprints.todoapp.RepositoryModule
import com.example.android.architecture.blueprints.todoapp.addedittask.AddEditTaskActivity
import com.example.android.architecture.blueprints.todoapp.addedittask.AddEditTaskContract
import com.example.android.architecture.blueprints.todoapp.addedittask.AddEditTaskFragment
import com.example.android.architecture.blueprints.todoapp.addedittask.AddEditTaskPresenter
import com.example.android.architecture.blueprints.todoapp.statistics.StatisticsActivity
import com.example.android.architecture.blueprints.todoapp.statistics.StatisticsContract
import com.example.android.architecture.blueprints.todoapp.statistics.StatisticsFragment
import com.example.android.architecture.blueprints.todoapp.statistics.StatisticsPresenter
import com.example.android.architecture.blueprints.todoapp.taskdetail.TaskDetailActivity
import com.example.android.architecture.blueprints.todoapp.taskdetail.TaskDetailContract
import com.example.android.architecture.blueprints.todoapp.taskdetail.TaskDetailFragment
import com.example.android.architecture.blueprints.todoapp.taskdetail.TaskDetailPresenter
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import com.example.android.architecture.blueprints.todoapp.tasks.TasksContract
import com.example.android.architecture.blueprints.todoapp.tasks.TasksFragment
import com.example.android.architecture.blueprints.todoapp.tasks.TasksPresenter
import org.koin.android.AndroidModule

/**
 * Koin modules
 */


fun moduleList() = arrayOf(RepositoryModule(), TasksActivityModule(), TaskDetailActivityModule(), StatisticsActivityModule(), AddEditTaskActivityModule())

class TasksActivityModule : AndroidModule() {
    override fun context() = declareContext {
        scope { TasksActivity::class }
        provide { TasksFragment() }
        provide(bind = TasksContract.Presenter::class) { TasksPresenter(get()) }
    }
}

class TaskDetailActivityModule : AndroidModule() {
    override fun context() = declareContext {
        scope { TaskDetailActivity::class }
        provide { TaskDetailFragment() }
        provide(bind = TaskDetailContract.Presenter::class) { TaskDetailPresenter(getProperty(TaskDetailActivity.EXTRA_TASK_ID), get()) }
    }
}

class StatisticsActivityModule : AndroidModule() {
    override fun context() = declareContext {
        scope { StatisticsActivity::class }
        provide { StatisticsFragment() }
        provide(bind = StatisticsContract.Presenter::class) { StatisticsPresenter(get()) }
    }
}

class AddEditTaskActivityModule : AndroidModule() {
    override fun context() = declareContext {
        scope { AddEditTaskActivity::class }
        provide { AddEditTaskFragment() }
        provide(bind = AddEditTaskContract.Presenter::class) { AddEditTaskPresenter(getProperty(AddEditTaskFragment.ARGUMENT_EDIT_TASK_ID), get(), getProperty(AddEditTaskActivity.SHOULD_LOAD_DATA_FROM_REPO_KEY)) }
    }
}