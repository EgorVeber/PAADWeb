package ru.gb.veber.lesson1mvplite.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.gb.veber.lesson1mvplite.model.StopwatchState

class MainViewModel : ViewModel() {
    private val _mutableLiveData: MutableLiveData<StopwatchState> = MutableLiveData()
    private val liveData: LiveData<StopwatchState> = _mutableLiveData

    fun subscribe(): MutableLiveData<StopwatchState> {
        return _mutableLiveData
    }

    fun start() {
        TODO("Not yet implemented")
    }

    fun pause() {
        TODO("Not yet implemented")
    }

    fun stop() {
        TODO("Not yet implemented")
    }
}