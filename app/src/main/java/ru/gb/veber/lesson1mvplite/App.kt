package ru.gb.veber.lesson1mvplite

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.gb.veber.lesson1mvplite.ServiceLocation.RepoModule
import ru.gb.veber.lesson1mvplite.ServiceLocation.mainModule

class App:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(mainModule, RepoModule)
        }
    }
}