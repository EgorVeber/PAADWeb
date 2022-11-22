//package ru.gb.veber.lesson1mvplite
//
//import android.os.Bundle
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import kotlinx.coroutines.*
//import org.koin.android.ext.android.get
//import org.koin.android.ext.android.inject
//import org.koin.core.parameter.parametersOf
//import org.koin.core.qualifier.named
//import ru.gb.veber.lesson1mvplite.databinding.ActivityMainBinding
//import kotlin.concurrent.thread
//import kotlin.coroutines.resume
//import kotlin.coroutines.suspendCoroutine
//
//class Lesson3 {
//    package ru.gb.veber.lesson1mvplite
//
//    import androidx.appcompat.app.AppCompatActivity
//    import android.os.Bundle
//    import android.widget.Toast
//    import kotlinx.coroutines.*
//    import org.koin.android.ext.android.get
//    import org.koin.android.ext.android.inject
//    import org.koin.core.parameter.parametersOf
//    import org.koin.core.qualifier.named
//    import ru.gb.veber.lesson1mvplite.databinding.ActivityMainBinding
//    import java.lang.Thread.sleep
//    import kotlin.concurrent.thread
//    import kotlin.coroutines.resume
//    import kotlin.coroutines.suspendCoroutine
//
//    class MainActivity : AppCompatActivity() {
//
//        private val repo: Repo = get()
//        private val cache: Cacheable by inject()
//        private val injectClass: InjectClass by inject()
//
//
//        private val repository: Repository by inject(named("Single"))
//        private val repositoryFactory: Repository by inject(named("Factory"))
//
//
//        private val someDepWithConstructor: SomeDepWithConstructor by inject {
//            parametersOf("Hello")
//        }
//
//
//        private val getContext: GetContext by inject()
//
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        setContentView(R.layout.activity_main)
////        //MainModule
////        repo.printSelf()
////        getKoin().get<Repo>().printSelf()
////        cache.printSelf()
////        cache.printSelf()
////        cache.printSelf()
////        injectClass.getCacheable()
////
////        //RepoModule
////        repository.getRepoImpl()
////        getKoin().get<Repository>(named("Single")).getRepoImpl()
////        repositoryFactory.getRepoImpl()
////
////
////        someDepWithConstructor.printSelf()
////
////        getKoin().get<SomeDepWithConstructor> { parametersOf("Hi") }.printSelf()
////
////        getContext.printSelf()
////    }
//
//
//        private lateinit var binding: ActivityMainBinding
//
//        val scope = CoroutineScope(Dispatchers.IO)
//
//        //val scope2 = kotlinx.coroutines.GlobalScope.launch(Dispatchers.IO)
//        private var job: Job? = null
//
//
//        private var deferred: Deferred<String>? = null
//
//
//        override fun onCreate(savedInstanceState: Bundle?) {
//            super.onCreate(savedInstanceState)
//            binding = ActivityMainBinding.inflate(layoutInflater)
//            setContentView(binding.root)
//
//            scope.launch {
//                while (true) {
//                    delay(1000)
//                    println("VVV 1 ")
//                }
//            }
//            scope.launch {
//                while (true) {
//                    delay(1000)
//                    println("VVV 2 ")
//                }
//            }
//            scope.launch {
//                while (true) {
//                    delay(1000)
//                    println("VVV 3 ")
//                }
//            }
//
//            deferred = scope.async {
//                delay(5000)
//                "New Text"
//            }
//
//
////        deferred = scope.async(start = CoroutineStart.LAZY) {// Только когда вызовем
////            delay(5000)
////            "New Text"
////        }
//
//            binding.button.setOnClickListener {
//                job?.cancel()
//                job = scope.launch {
//
//                    val url = buildUrl()
//                    download(url)
//
//                    withContext(Dispatchers.Main) {
//                        binding.button.text = deferred?.await()
//                    }
//
//                    withContext(Dispatchers.Main) {
//                        Toast.makeText(this@MainActivity, "finish", Toast.LENGTH_SHORT).show()
//                    }
//                }
//                //  scope.cancel()
//            }
//        }
//
//        override fun onDestroy() {
//            super.onDestroy()
//            scope.cancel()
//        }
//
//        private fun buildUrl() = "url"
//
//
//        private suspend fun download(url: String) = suspendCoroutine<Unit> {
//            thread {
//                Thread.sleep(2000)
//                it.resume(Unit)
//            }
//        }
//    }
//}