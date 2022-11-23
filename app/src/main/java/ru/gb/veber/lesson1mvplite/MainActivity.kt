package ru.gb.veber.lesson1mvplite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.gb.veber.lesson1mvplite.databinding.ActivityMainBinding
import java.util.concurrent.CopyOnWriteArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

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
        CoroutineScope(
            Dispatchers.Main
                    + SupervisorJob()
        )
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.Main + SupervisorJob()).launch {
            stopwatchListOrchestrator.ticker.collect {
                binding.textTime.text = it
            }
        }

        binding.buttonStart.setOnClickListener {
            stopwatchListOrchestrator.start()
        }

        binding.buttonPause.setOnClickListener {
            stopwatchListOrchestrator.pause()
        }

        binding.buttonStop.setOnClickListener {
            stopwatchListOrchestrator.stop()
        }
    }

    companion object {
        const val LOG_COROUTINE_TAG = "LOG_COROUTINE_TAG"
    }

    private fun logD(message: Any) {
        Log.d(LOG_COROUTINE_TAG, message.toString())
    }
}
