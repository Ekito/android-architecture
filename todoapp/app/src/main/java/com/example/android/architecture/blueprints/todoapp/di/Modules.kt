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
 * Created by arnaud on 06/09/2017.
 */


fun moduleList() = arrayOf(RepositoryModule(), TasksActivityModule(), TaskDetailActivityModule(), StatisticsActivityModule(), AddEditTaskActivityModule())

class TasksActivityModule : AndroidModule() {
    override fun context() = declareContext {
        scope { TasksActivity::class }
        provide { TasksFragment() }
        provide { TasksPresenter(get()) } bind { TasksContract.Presenter::class }
    }
}

class TaskDetailActivityModule : AndroidModule() {
    override fun context() = declareContext {
        scope { TaskDetailActivity::class }
        provide { TaskDetailFragment() }
        provide { TaskDetailPresenter(getProperty(TaskDetailActivity.EXTRA_TASK_ID), get()) } bind { TaskDetailContract.Presenter::class }
    }
}

class StatisticsActivityModule : AndroidModule() {
    override fun context() = declareContext {
        scope { StatisticsActivity::class }
        provide { StatisticsFragment() }
        provide { StatisticsPresenter(get()) } bind { StatisticsContract.Presenter::class }
    }
}

class AddEditTaskActivityModule : AndroidModule() {
    override fun context() = declareContext {
        scope { AddEditTaskActivity::class }
        provide { AddEditTaskFragment() }
        provide { AddEditTaskPresenter(getProperty(AddEditTaskFragment.ARGUMENT_EDIT_TASK_ID), get(), getProperty(AddEditTaskActivity.SHOULD_LOAD_DATA_FROM_REPO_KEY)) } bind { AddEditTaskContract.Presenter::class }
    }
}