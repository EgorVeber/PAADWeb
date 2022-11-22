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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonStart.setOnClickListener {
            coroutineScope.launch {
                launch {
                    delay(3000)
                    10 / 0
                }
                while (true) {
                    delay(1000)
                    printLog("tick1")
                }
            }

            coroutineScope.launch {
                while (true) {
                    delay(1000)
                    printLog("tick2")
                }
            }
        }

        binding.buttonStop.setOnClickListener {

        }
    }

    companion object {
        const val LOG_COROUTINE_TAG = "LOG_COROUTINE_TAG"
    }

    private fun printLog(message: Any) {
        Log.d(LOG_COROUTINE_TAG, message.toString())
    }
}