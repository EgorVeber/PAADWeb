package ru.gb.veber.lesson1mvplite

import android.os.Bundle
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
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

class ViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            MainViewModel::class.java -> {
                MainViewModel()
            }
            else -> {
                throw IllegalStateException("Unknown view model class")
            }
        }
        return viewModel as T
    }
}

//class ViewModelFactory2(savedStateRegistryOwner: SavedStateRegistryOwner) :
//    AbstractSavedStateViewModelFactory(savedStateRegistryOwner, null) {
//    override fun <T : ViewModel?> create(
//        key: String,
//        modelClass: Class<T>,
//        handle: SavedStateHandle,
//    ): T {
//        val viewModel = when (modelClass) {
//            ViewModelNumber::class.java -> {
//                ViewModelNumber(savedStateHandle = handle)
//            }
//            else -> {
//                throw IllegalStateException("Unknown view model class")
//            }
//        }
//        return viewModel as T
//    }
//}

//companion object {
//    const val BUNDLE_KEY = "BUNDLE_KEY"
//    fun provideFactory(
//        myRepository: Repo,
//        owner: SavedStateRegistryOwner,
//        defaultArgs: Bundle? = null,
//    ): AbstractSavedStateViewModelFactory =
//        object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
//            @Suppress("UNCHECKED_CAST")
//            override fun <T : ViewModel> create(
//                key: String,
//                modelClass: Class<T>,
//                handle: SavedStateHandle,
//            ): T {
//                return MyViewModel(myRepository, handle) as T
//            }
//        }
//}