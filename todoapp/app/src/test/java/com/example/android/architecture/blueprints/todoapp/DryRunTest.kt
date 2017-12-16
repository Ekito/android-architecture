package com.example.android.architecture.blueprints.todoapp

import android.app.Application
import com.example.android.architecture.blueprints.todoapp.di.Properties.ARGUMENT_EDIT_TASK_ID
import com.example.android.architecture.blueprints.todoapp.di.Properties.EXTRA_TASK_ID
import com.example.android.architecture.blueprints.todoapp.di.Properties.SHOULD_LOAD_DATA_FROM_REPO_KEY
import com.example.android.architecture.blueprints.todoapp.di.todoAppModules
import org.junit.Test
import org.koin.android.ext.koin.with
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.test.KoinTest
import org.koin.test.dryRun
import org.mockito.Mockito.mock

/**
 * Test Dry run / Koin Context
 */
class DryRunTest : KoinTest {

    @Test
    fun todoAppModuleDryRun() {
        startKoin(todoAppModules,
                properties = mapOf(
                        EXTRA_TASK_ID to "",
                        ARGUMENT_EDIT_TASK_ID to "",
                        SHOULD_LOAD_DATA_FROM_REPO_KEY to false)) with (mock(Application::class.java))
        dryRun()
    }
}