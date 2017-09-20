package com.example.android.architecture.blueprints.todoapp

import android.content.Context
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
import org.koin.android.ext.android.app.getKoin

/**
 * Test Injection Helper
 */
object Injection {

    @JvmStatic
    fun provideTasksRepository(context: Context): TasksRepository = context.getKoin().get()

}