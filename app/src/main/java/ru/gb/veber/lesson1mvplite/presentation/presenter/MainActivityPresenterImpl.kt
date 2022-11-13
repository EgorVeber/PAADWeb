package ru.gb.veber.lesson1mvplite.presentation.presenter

import ru.gb.veber.lesson1mvplite.data.Repo
import ru.gb.veber.lesson1mvplite.presentation.MainActivityView

class MainActivityPresenterImpl(private val repo: Repo) : MainActivityPresenter {

    private var mainActivityView: MainActivityView? = null

    override fun attach(mainActivityView: MainActivityView) {
        this.mainActivityView = mainActivityView
    }

    override fun getData() {
        val data = repo.getData()
        mainActivityView?.showData(data)
    }

    override fun detach() {
        this.mainActivityView = null
    }
}