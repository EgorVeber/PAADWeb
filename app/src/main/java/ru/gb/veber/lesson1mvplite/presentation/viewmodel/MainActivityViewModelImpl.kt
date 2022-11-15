package ru.gb.veber.lesson1mvplite.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import ru.gb.veber.lesson1mvplite.data.Repo
import kotlin.concurrent.thread

class MainActivityViewModelImpl : MainActivityViewModel.ViewModel()  {
    override val data = MutableLiveData<String>()

    private val repo = Repo()

    override fun loadData() {
        val getData = repo.getData()
        thread {
            Thread.sleep(2000)
            data.postValue(getData)
        }
    }
}