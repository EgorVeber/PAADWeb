package ru.gb.veber.lesson1mvplite

import android.content.Context

interface Repository {
    fun getRepoImpl()
}

class RepoImplRoom() : Repository {
    override fun getRepoImpl() {
        println("VVV $this")
    }
}

class RepoImplRetrofit(private val someDep: SomeDep) : Repository {
    override fun getRepoImpl() {
        println("VVV $this")
        someDep.printSelf()
    }
}

class SomeDep() {
    fun printSelf() {
        println("VVV $this")
    }
}

class GetContext(private val context: Context) {
    fun printSelf() {
        println("VVV" + context.getString(R.string.app_name))
    }
}


class SomeDepWithConstructor(private val string: String) {
    fun printSelf() {
        println("VVV $string")
    }
}