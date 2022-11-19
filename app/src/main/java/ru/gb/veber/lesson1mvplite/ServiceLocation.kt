package ru.gb.veber.lesson1mvplite

import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.util.*

object ServiceLocation {
    val mainModule = module {
        factory {
            Repo()
        }
        single {
            Cacheable()
        }
        single {
            InjectClass()
        }
    }

    val RepoModule = module {

        single {
            SomeDep()
        }

        single {
            GetContext(androidContext())
        }

        single<Repository>(named("Single")) {
            RepoImplRoom()
        }
        factory<Repository>(named("Factory")) {
            RepoImplRetrofit(someDep = get())
        }

        single { (data: String) ->
            SomeDepWithConstructor(data)
        }
    }
}

class Cacheable {
    fun printSelf() {
        println("VVV $this")
    }
}

class Repo {
    fun printSelf() {
        println("VVV $this")
    }
}


class InjectClass() : KoinComponent {
    private val cacheable: Cacheable by inject()

    fun getCacheable() {
        cacheable.printSelf()
    }
}
