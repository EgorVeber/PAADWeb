package ru.gb.veber.lesson1mvplite.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.*
import ru.gb.veber.lesson1mvplite.*
import ru.gb.veber.lesson1mvplite.databinding.ActivityMainBinding
import ru.gb.veber.lesson1mvplite.model.StopwatchState
import ru.gb.veber.lesson1mvplite.model.StopwatchStateHolder
import ru.gb.veber.lesson1mvplite.model.statestopwatch.ElapsedTimeCalculator
import ru.gb.veber.lesson1mvplite.model.statestopwatch.StopwatchStateCalculator
import ru.gb.veber.lesson1mvplite.utils.TimestampMillisecondsFormatter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    private val model by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }


    private val timestampProvider = object : TimestampProvider {
        override fun getMilliseconds(): Long {
            return System.currentTimeMillis()
        }
    }

    private val stopwatchListOrchestrator = StopwatchListOrchestrator(
        StopwatchStateHolder(
            StopwatchStateCalculator(
                timestampProvider,
                ElapsedTimeCalculator(timestampProvider)),
            ElapsedTimeCalculator(timestampProvider),
            TimestampMillisecondsFormatter()
        ),
        CoroutineScope(Dispatchers.Main + SupervisorJob())
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        model.subscribe().observe(this) {
            renderDate(it)
        }


        CoroutineScope(Dispatchers.Main + SupervisorJob()).launch {
            stopwatchListOrchestrator.ticker.collect {
                binding.textTime.text = it
            }
        }

        binding.buttonStart.setOnClickListener {
            model.start()
            stopwatchListOrchestrator.start()
        }

        binding.buttonPause.setOnClickListener {
            model.pause()
            stopwatchListOrchestrator.pause()
        }

        binding.buttonStop.setOnClickListener {
            model.stop()
            stopwatchListOrchestrator.stop()
        }
    }

    private fun renderDate(it: StopwatchState) {
        logD(it)
    }

    companion object {
        const val LOG_COROUTINE_TAG = "LOG_COROUTINE_TAG"
    }

    private fun logD(message: Any) {
        Log.d(LOG_COROUTINE_TAG, message.toString())
    }
}
