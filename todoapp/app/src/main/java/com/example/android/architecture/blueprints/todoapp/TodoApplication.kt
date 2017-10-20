package com.example.android.architecture.blueprints.todoapp

import android.app.Application
import com.example.android.architecture.blueprints.todoapp.di.moduleList
import org.koin.android.ext.koin.startAndroidContext

/**
 * Todo Application - main class
 */
class TodoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startAndroidContext(this, moduleList())
    }
}