package ru.gb.veber.lesson1mvplite.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

interface MainActivityViewModel {
    abstract class ViewModel: androidx.lifecycle.ViewModel() {
        abstract val data: LiveData<String>
        abstract fun loadData()
    }
}