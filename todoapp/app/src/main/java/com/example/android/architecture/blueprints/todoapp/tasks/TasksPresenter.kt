/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.architecture.blueprints.todoapp.tasks

import android.app.Activity
import com.example.android.architecture.blueprints.todoapp.addedittask.AddEditTaskActivity
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.TasksDataSource
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
import com.example.android.architecture.blueprints.todoapp.di.Properties.CURRENT_FILTERING_KEY
import com.example.android.architecture.blueprints.todoapp.util.EspressoIdlingResource
import org.koin.standalone.KoinComponent
import org.koin.standalone.setProperty
import java.util.*

/**
 * Listens to user actions from the UI ([TasksFragment]), retrieves the data and updates the
 * UI as required.
 */
class TasksPresenter(override var currentFiltering: TasksFilterType, private val tasksRepository: TasksRepository)
    : TasksContract.Presenter, KoinComponent {

    override lateinit var view: TasksContract.View

    private var firstLoad = true

    override fun start() {
        loadTasks(false)
    }

    override fun stop() {
        setProperty(CURRENT_FILTERING_KEY, currentFiltering)
    }

    override fun result(requestCode: Int, resultCode: Int) {
        // If a task was successfully added, show snackbar
        if (AddEditTaskActivity.REQUEST_ADD_TASK ==
                requestCode && Activity.RESULT_OK == resultCode) {
            view.showSuccessfullySavedMessage()
        }
    }

    override fun loadTasks(forceUpdate: Boolean) {
        // Simplification for sample: a network reload will be forced on first load.
        loadTasks(forceUpdate || firstLoad, true)
        firstLoad = false
    }

    /**
     * @param forceUpdate   Pass in true to refresh the data in the [TasksDataSource]
     * *
     * @param showLoadingUI Pass in true to display a loading icon in the UI
     */
    private fun loadTasks(forceUpdate: Boolean, showLoadingUI: Boolean) {
        if (showLoadingUI) {
            view.setLoadingIndicator(true)
        }
        if (forceUpdate) {
            tasksRepository.refreshTasks()
        }

        // The network request might be handled in a different thread so make sure Espresso knows
        // that the app is busy until the response is handled.
        EspressoIdlingResource.increment() // App is busy until further notice

        tasksRepository.getTasks(object : TasksDataSource.LoadTasksCallback {
            override fun onTasksLoaded(tasks: List<Task>) {
                val tasksToShow = ArrayList<Task>()

                // This callback may be called twice, once for the cache and once for loading
                // the data from the server API, so we check before decrementing, otherwise
                // it throws "Counter has been corrupted!" exception.
                if (!EspressoIdlingResource.countingIdlingResource.isIdleNow) {
                    EspressoIdlingResource.decrement() // Set app as idle.
                }

                // We filter the tasks based on the requestType
                for (task in tasks) {
                    when (currentFiltering) {
                        TasksFilterType.ALL_TASKS -> tasksToShow.add(task)
                        TasksFilterType.ACTIVE_TASKS -> if (task.isActive) {
                            tasksToShow.add(task)
                        }
                        TasksFilterType.COMPLETED_TASKS -> if (task.isCompleted) {
                            tasksToShow.add(task)
                        }
                    }
                }
                // The view may not be able to handle UI updates anymore
                if (!view.isActive) {
                    return
                }
                if (showLoadingUI) {
                    view.setLoadingIndicator(false)
                }

                processTasks(tasksToShow)
            }

            override fun onDataNotAvailable() {
                // The view may not be able to handle UI updates anymore
                if (!view.isActive) {
                    return
                }
                view.showLoadingTasksError()
            }
        })
    }

    private fun processTasks(tasks: List<Task>) {
        if (tasks.isEmpty()) {
            // Show a message indicating there are no tasks for that filter type.
            processEmptyTasks()
        } else {
            // Show the list of tasks
            view.showTasks(tasks)
            // Set the filter label's text.
            showFilterLabel()
        }
    }

    private fun showFilterLabel() {
        when (currentFiltering) {
            TasksFilterType.ACTIVE_TASKS -> view.showActiveFilterLabel()
            TasksFilterType.COMPLETED_TASKS -> view.showCompletedFilterLabel()
            else -> view.showAllFilterLabel()
        }
    }

    private fun processEmptyTasks() {
        when (currentFiltering) {
            TasksFilterType.ACTIVE_TASKS -> view.showNoActiveTasks()
            TasksFilterType.COMPLETED_TASKS -> view.showNoCompletedTasks()
            else -> view.showNoTasks()
        }
    }

    override fun addNewTask() {
        view.showAddTask()
    }

    override fun openTaskDetails(requestedTask: Task) {
        view.showTaskDetailsUi(requestedTask.id)
    }

    override fun completeTask(completedTask: Task) {
        tasksRepository.completeTask(completedTask)
        view.showTaskMarkedComplete()
        loadTasks(false, false)
    }

    override fun activateTask(activeTask: Task) {
        tasksRepository.activateTask(activeTask)
        view.showTaskMarkedActive()
        loadTasks(false, false)
    }

    override fun clearCompletedTasks() {
        tasksRepository.clearCompletedTasks()
        view.showCompletedTasksCleared()
        loadTasks(false, false)
    }

}
