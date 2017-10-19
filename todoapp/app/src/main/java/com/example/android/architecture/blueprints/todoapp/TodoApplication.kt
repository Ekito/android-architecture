package com.example.android.architecture.blueprints.todoapp

import android.app.Application
import com.example.android.architecture.blueprints.todoapp.di.moduleList
import org.koin.android.ext.koin.newKoinContext

/**
 * Todo Application - main class
 */
class TodoApplication : Application() {

    override fun onCreate() {
//        super.onCreate()
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return
//        }
//        LeakCanary.install(this)

        newKoinContext(this, moduleList())
    }
}