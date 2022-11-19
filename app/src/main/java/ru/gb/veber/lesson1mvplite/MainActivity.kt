package ru.gb.veber.lesson1mvplite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.android.ext.android.get
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

class MainActivity : AppCompatActivity() {

    private val repo: Repo  = get()
    private val cache: Cacheable by inject()
    private val injectClass: InjectClass by inject()


    private val repository: Repository by inject(named("Single"))
    private val repositoryFactory: Repository by inject(named("Factory"))


    private val someDepWithConstructor: SomeDepWithConstructor by inject {
        parametersOf("Hello")
    }

    private val getContext:GetContext by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //MainModule
        repo.printSelf()
        getKoin().get<Repo>().printSelf()
        cache.printSelf()
        cache.printSelf()
        cache.printSelf()
        injectClass.getCacheable()

        //RepoModule
        repository.getRepoImpl()
        getKoin().get<Repository>(named("Single")).getRepoImpl()
        repositoryFactory.getRepoImpl()


        someDepWithConstructor.printSelf()

        getKoin().get<SomeDepWithConstructor> { parametersOf("Hi") }.printSelf()

        getContext.printSelf()
    }
}