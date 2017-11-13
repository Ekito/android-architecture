package com.example.android.architecture.blueprints.todoapp

import android.app.Application
import android.content.Context
import com.example.android.architecture.blueprints.todoapp.addedittask.AddEditTaskActivity
import com.example.android.architecture.blueprints.todoapp.addedittask.AddEditTaskFragment
import com.example.android.architecture.blueprints.todoapp.di.todoAppModules
import com.example.android.architecture.blueprints.todoapp.taskdetail.TaskDetailActivity
import org.junit.Test
import org.koin.dsl.module.Module
import org.koin.test.KoinTest
import org.koin.test.dryRun
import org.mockito.Mockito

/**
 * Test Dry run / Koin Context
 */
class DryRunTest : KoinTest {

    class MockedValuesModule : Module() {
        override fun context() = applicationContext {
            //Mock context
            provide { Mockito.mock(Application::class.java) } bind Context::class

            // default values
            properties(listOf(
                    TaskDetailActivity.EXTRA_TASK_ID to "",
                    AddEditTaskFragment.ARGUMENT_EDIT_TASK_ID to "",
                    AddEditTaskActivity.SHOULD_LOAD_DATA_FROM_REPO_KEY to false)
            )
        }
    }

    @Test
    fun todoAppModuleDryRun() {
        dryRun(listOf(MockedValuesModule()) + todoAppModules())
    }
}