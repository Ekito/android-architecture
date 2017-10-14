package com.example.android.architecture.blueprints.todoapp

import android.app.Application
import com.example.android.architecture.blueprints.todoapp.addedittask.AddEditTaskActivity
import com.example.android.architecture.blueprints.todoapp.addedittask.AddEditTaskFragment
import com.example.android.architecture.blueprints.todoapp.di.moduleList
import com.example.android.architecture.blueprints.todoapp.taskdetail.TaskDetailActivity
import org.junit.Test
import org.koin.Koin
import org.koin.android.init
import org.mockito.Mockito

/**
 * Test Dry run / Koin Context
 */
class DryRunTest {
    @Test
    fun todoAppModuleDryRun() {
        Koin().init(Mockito.mock(Application::class.java))
                // bootstrap with default values
                .properties(mapOf(TaskDetailActivity.EXTRA_TASK_ID to "", AddEditTaskFragment.ARGUMENT_EDIT_TASK_ID to "", AddEditTaskActivity.SHOULD_LOAD_DATA_FROM_REPO_KEY to false))
                .build(moduleList()).dryRun()
    }
}