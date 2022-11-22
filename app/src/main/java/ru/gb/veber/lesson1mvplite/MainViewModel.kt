package ru.gb.veber.lesson1mvplite

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

internal class MainViewModel(
    repository: Repository = Repository(),
) : ViewModel() {
    val liveData: MutableLiveData<Data> = MutableLiveData()
   //val liveData: LiveData<Data> = repository.userData.asLiveData()


    init {
        viewModelScope.launch {
            repository.userData.flowOn(Dispatchers.Main)
                .collect { data ->
                    liveData.value = data
                }
        }
    }
}