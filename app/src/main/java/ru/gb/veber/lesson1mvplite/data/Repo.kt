package ru.gb.veber.lesson1mvplite.data

import ru.gb.veber.lesson1mvplite.entities.Data

interface Repo {
    fun getData():Data
}