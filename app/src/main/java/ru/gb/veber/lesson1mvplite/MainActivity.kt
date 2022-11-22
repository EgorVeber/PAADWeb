package ru.gb.veber.lesson1mvplite

import android.location.GnssAntennaInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import ru.gb.veber.lesson1mvplite.databinding.ActivityMainBinding
import java.util.concurrent.CopyOnWriteArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        printLog(throwable)
    }
    private val coroutineScope =
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler + SupervisorJob())


    private var job: Job? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var flow = flow {
            repeat(10) {
                delay(400)
                emit(it)
            }
        }

        var flowList = flowOf(listOf(1, 2, 3, 4), listOf(1, 2, 3, 4))

        var flowListExtension = listOf(1, 2, 3, 4).asFlow()

        binding.buttonStart.setOnClickListener {
            job?.cancel()
            job = coroutineScope.launch {
                flowList.collect {
                    printLog(it)
                }
            }
        }

        binding.buttonStop.setOnClickListener {
            job?.cancel()
        }
    }

    companion object {
        const val LOG_COROUTINE_TAG = "LOG_COROUTINE_TAG"
    }

    private fun printLog(message: Any) {
        Log.d(LOG_COROUTINE_TAG, message.toString())
    }

    private fun callBackFlow(someCallbackClass: SomeCallbackClass) = callbackFlow {
        var numder = 1000
        var listener = SomeCallbackClass.Listener {
            trySend(numder++)
        }
        someCallbackClass.addListener(listener)
        awaitClose { someCallbackClass.remove(listener) }
    }
}

class SomeCallbackClass() {
    fun interface Listener {
        fun onChange()
    }

    val listeners = CopyOnWriteArrayList<Listener>()

    fun addListener(listener: Listener) {
        listeners.add(listener)
    }

    fun remove(listener: Listener) = listeners.remove(listener)

    fun invoke() {
        listeners.forEach { it.onChange() }
    }
}