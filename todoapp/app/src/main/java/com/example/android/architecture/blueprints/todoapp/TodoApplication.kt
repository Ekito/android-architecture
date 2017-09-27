package com.example.android.architecture.blueprints.todoapp

import android.app.Application
import com.example.android.architecture.blueprints.todoapp.di.moduleList
import org.koin.Koin
import org.koin.KoinContext
import org.koin.android.KoinContextAware
import org.koin.android.init

/**
 * Created by arnaud on 06/09/2017.
 */
class TodoApplication : Application(), KoinContextAware {

    lateinit var koinContext: KoinContext
    override fun getKoin(): KoinContext = koinContext

    override fun onCreate() {
        super.onCreate()
        koinContext = Koin().init(this).build(*moduleList())
    }
}