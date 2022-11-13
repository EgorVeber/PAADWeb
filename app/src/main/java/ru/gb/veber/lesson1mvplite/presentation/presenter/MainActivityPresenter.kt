package ru.gb.veber.lesson1mvplite.presentation.presenter

import ru.gb.veber.lesson1mvplite.presentation.MainActivityView

interface MainActivityPresenter {
    fun attach(mainActivityView: MainActivityView)
    fun getData()
    fun detach()
}