package ru.gb.veber.lesson1mvplite

import android.app.Application
import ru.gb.veber.lesson1mvplite.data.RepoImpl
import ru.gb.veber.lesson1mvplite.presentation.presenter.MainActivityPresenterImpl

class App : Application() {
    lateinit var presenterImpl: MainActivityPresenterImpl
    override fun onCreate() {
        super.onCreate()
        instance = this
        presenterImpl = MainActivityPresenterImpl(RepoImpl())
    }

    companion object {
        lateinit var instance: App
            private set
    }
}