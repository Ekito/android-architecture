package com.example.android.architecture.blueprints.todoapp

import android.app.Application
import android.content.Context
import com.example.android.architecture.blueprints.todoapp.di.TodoAppModule.Properties.ARGUMENT_EDIT_TASK_ID
import com.example.android.architecture.blueprints.todoapp.di.TodoAppModule.Properties.EXTRA_TASK_ID
import com.example.android.architecture.blueprints.todoapp.di.TodoAppModule.Properties.SHOULD_LOAD_DATA_FROM_REPO_KEY
import com.example.android.architecture.blueprints.todoapp.di.todoAppModules
import org.junit.Test
import org.koin.dsl.module.Module
import org.koin.standalone.startKoin
import org.koin.test.KoinTest
import org.koin.test.dryRun
import org.mockito.Mockito

/**
 * Test Dry run / Koin Context
 */
class DryRunTest : KoinTest {

    class MockAndroid : Module() {
        override fun context() = applicationContext {
            //Mock context
            provide { Mockito.mock(Application::class.java) } bind Context::class
        }
    }

    @Test
    fun todoAppModuleDryRun() {
        startKoin(listOf(MockAndroid()) + todoAppModules(),
                properties = mapOf(
                        EXTRA_TASK_ID to "",
                        ARGUMENT_EDIT_TASK_ID to "",
                        SHOULD_LOAD_DATA_FROM_REPO_KEY to false))
        dryRun()
    }
}