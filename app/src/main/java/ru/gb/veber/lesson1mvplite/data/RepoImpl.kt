package ru.gb.veber.lesson1mvplite.data

import ru.gb.veber.lesson1mvplite.entities.Data

class RepoImpl:Repo {
    override fun getData(): Data = Data("Some data")
}