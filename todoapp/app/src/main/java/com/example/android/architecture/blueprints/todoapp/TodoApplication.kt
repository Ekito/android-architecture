package com.example.android.architecture.blueprints.todoapp

import android.app.Application
import android.support.multidex.MultiDexApplication
import com.example.android.architecture.blueprints.todoapp.koin.TasksActivityModule
import org.koin.Koin
import org.koin.KoinContext
import org.koin.android.KoinContextAware
import org.koin.android.init

/**
 * Created by arnaud on 06/09/2017.
 */
class TodoApplication : Application(), KoinContextAware {

    override fun getKoin(): KoinContext = koinContext

    override fun onCreate() {
        super.onCreate()

        koinContext = Koin().init(this).build(RepositoryModule(), TasksActivityModule())
    }

    companion object {
        lateinit var koinContext: KoinContext
    }
}