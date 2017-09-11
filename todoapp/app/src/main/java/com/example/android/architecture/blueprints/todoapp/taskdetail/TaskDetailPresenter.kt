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
package com.example.android.architecture.blueprints.todoapp.taskdetail

import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.TasksDataSource
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository

/**
 * Listens to user actions from the UI ([TaskDetailFragment]), retrieves the data and updates
 * the UI as required.
 */
class TaskDetailPresenter(
        private val taskId: String,
        private val tasksRepository: TasksRepository
        ) : TaskDetailContract.Presenter {

    override lateinit var view: TaskDetailContract.View

    override fun start() {
        openTask()
    }

    private fun openTask() {
        if (taskId.isEmpty()) {
            view.showMissingTask()
            return
        }

        view.setLoadingIndicator(true)
        tasksRepository.getTask(taskId, object : TasksDataSource.GetTaskCallback {
            override fun onTaskLoaded(task: Task) {
                with(view) {
                    // The view may not be able to handle UI updates anymore
                    if (!isActive) {
                        return@onTaskLoaded
                    }
                    setLoadingIndicator(false)
                }
                showTask(task)
            }

            override fun onDataNotAvailable() {
                with(view) {
                    // The view may not be able to handle UI updates anymore
                    if (!isActive) {
                        return@onDataNotAvailable
                    }
                    showMissingTask()
                }
            }
        })
    }

    override fun editTask() {
        if (taskId.isEmpty()) {
            view.showMissingTask()
            return
        }
        view.showEditTask(taskId)
    }

    override fun deleteTask() {
        if (taskId.isEmpty()) {
            view.showMissingTask()
            return
        }
        tasksRepository.deleteTask(taskId)
        view.showTaskDeleted()
    }

    override fun completeTask() {
        if (taskId.isEmpty()) {
            view.showMissingTask()
            return
        }
        tasksRepository.completeTask(taskId)
        view.showTaskMarkedComplete()
    }

    override fun activateTask() {
        if (taskId.isEmpty()) {
            view.showMissingTask()
            return
        }
        tasksRepository.activateTask(taskId)
        view.showTaskMarkedActive()
    }

    private fun showTask(task: Task) {
        with(view) {
            if (taskId.isEmpty()) {
                hideTitle()
                hideDescription()
            } else {
                showTitle(task.title)
                showDescription(task.description)
            }
            showCompletionStatus(task.isCompleted)
        }
    }
}
