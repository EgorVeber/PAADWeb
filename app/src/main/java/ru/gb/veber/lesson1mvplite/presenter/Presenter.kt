package ru.gb.veber.lesson1mvplite.presenter

import ru.gb.veber.lesson1mvplite.data.Repo

class Presenter {
    val repo = Repo()
    fun getData() = repo.getData()
}