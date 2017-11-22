package com.example.android.architecture.blueprints.todoapp

import android.app.Application
import android.content.pm.ApplicationInfo
import com.example.android.architecture.blueprints.todoapp.di.todoAppModules
import org.koin.Koin
import org.koin.android.ext.android.startKoin
import org.koin.android.logger.AndroidLogger

/**
 * Todo Application - main class
 *
 * Just run Koin context with startAndroidContext()
 *
 */
class TodoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin(this, todoAppModules())

        // Display some logs
        val isDebug = (0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE)
        if (isDebug) {
            Koin.logger = AndroidLogger()
        }
    }
}