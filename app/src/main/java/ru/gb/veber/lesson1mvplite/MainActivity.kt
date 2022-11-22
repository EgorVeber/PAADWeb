package ru.gb.veber.lesson1mvplite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import ru.gb.veber.lesson1mvplite.databinding.ActivityMainBinding

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


        val flag = true
        binding.buttonStart.setOnClickListener {
            job?.cancel()
            job = coroutineScope.launch {
                while (isActive) {
                    delay(400)
                    printLog(isActive)
                    printLog(job?.isCancelled.toString())
                }
            }
        }

        binding.buttonStop.setOnClickListener {
            if (flag) job?.cancel() else job?.start()
        }
    }

    companion object {
        const val LOG_COROUTINE_TAG = "LOG_COROUTINE_TAG"
    }

    private fun printLog(message: Any) {
        Log.d(LOG_COROUTINE_TAG, message.toString())
    }
}