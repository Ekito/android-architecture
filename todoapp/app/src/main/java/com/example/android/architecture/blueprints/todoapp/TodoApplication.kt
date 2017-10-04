package com.example.android.architecture.blueprints.todoapp

import android.app.Application
import com.example.android.architecture.blueprints.todoapp.di.moduleList
import org.koin.android.KoinContextAware
import org.koin.android.newKoinContext

/**
 * Todo Application - main class
 */
class TodoApplication : Application(), KoinContextAware {

    // Koin Context
    override val koinContext = newKoinContext(this, moduleList())

}