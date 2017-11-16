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

package com.example.android.architecture.blueprints.todoapp.addedittask

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.di.TodoAppModule.Properties.ARGUMENT_EDIT_TASK_ID
import com.example.android.architecture.blueprints.todoapp.util.replaceFragmentInActivity
import com.example.android.architecture.blueprints.todoapp.util.setupActionBar
import org.koin.android.ext.android.bindProperty
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.property

/**
 * Displays an add or edit task screen.
 */
class AddEditTaskActivity : AppCompatActivity() {

    private val addEditTaskFragment: AddEditTaskFragment by inject()

    private val taskId by property(ARGUMENT_EDIT_TASK_ID, "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addtask_act)

//        val taskId: String? = intent.getStringExtra(AddEditTaskFragment.ARGUMENT_EDIT_TASK_ID)
//        bindProperty(AddEditTaskFragment.ARGUMENT_EDIT_TASK_ID, taskId ?: "")

        bindProperty(ARGUMENT_EDIT_TASK_ID, taskId)

        // Set up the toolbar.
        setupActionBar(R.id.toolbar) {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setTitle(if (taskId.isEmpty()) R.string.add_task else R.string.edit_task)
        }

        supportFragmentManager
                .findFragmentById(R.id.contentFrame) as AddEditTaskFragment?
                ?: addEditTaskFragment.also {
            replaceFragmentInActivity(addEditTaskFragment, R.id.contentFrame)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val REQUEST_ADD_TASK = 1
    }
}