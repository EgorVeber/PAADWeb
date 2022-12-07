package ru.gb.veber.lesson1mvplite.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.gb.veber.lesson1mvplite.StopwatchListOrchestrator
import ru.gb.veber.lesson1mvplite.TimestampProvider
import ru.gb.veber.lesson1mvplite.model.StopwatchStateHolder
import ru.gb.veber.lesson1mvplite.model.statestopwatch.ElapsedTimeCalculator
import ru.gb.veber.lesson1mvplite.model.statestopwatch.StopwatchStateCalculator
import ru.gb.veber.lesson1mvplite.utils.TimestampMillisecondsFormatter

class MainViewModel : ViewModel() {
    private val _mutableLiveData: MutableLiveData<String> = MutableLiveData()
    private val liveData: LiveData<String> = _mutableLiveData

    init {
        viewModelScope.launch(Dispatchers.Main + SupervisorJob()) {
            stopwatchListOrchestrator.ticker.collect {
                _mutableLiveData.postValue(it)
            }
        }
    }

    private val timestampProvider = object : TimestampProvider {
        override fun getMilliseconds(): Long {
            return System.currentTimeMillis()
        }
    }

    fun subscribe() = liveData

    fun start() = stopwatchListOrchestrator.start()
    fun pause() = stopwatchListOrchestrator.pause()
    fun stop() = stopwatchListOrchestrator.stop()


    private val stopwatchListOrchestrator = StopwatchListOrchestrator(
        StopwatchStateHolder(
            StopwatchStateCalculator(
                timestampProvider,
                ElapsedTimeCalculator(timestampProvider)),
            ElapsedTimeCalculator(timestampProvider),
            TimestampMillisecondsFormatter()
        ), viewModelScope
    )
}