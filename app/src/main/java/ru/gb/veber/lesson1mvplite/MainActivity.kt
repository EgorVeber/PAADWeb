package ru.gb.veber.lesson1mvplite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.gb.veber.lesson1mvplite.databinding.ActivityMainBinding
import java.util.concurrent.CopyOnWriteArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val coroutineModel: MainViewModel by lazy {
        ViewModelProvider(this,ViewModelFactory())[MainViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        getFlow()
//        startFlow()


//        setupFlows()
//        zipFlows()
        coroutineModel.liveData.observe(this) {
            binding.message.text = it.data
        }
    }

    private fun getFlow() = flow {
        logD("Start Flow")
        (0..10).forEach {
            delay(500)
            logD("Emmitting $it")
            emit(it)
        }
    }.flowOn(Dispatchers.Default)

    private fun startFlow() {
        CoroutineScope(Dispatchers.Main).launch {
            getFlow().collect {
                logD(it)
            }
        }
    }


    lateinit var flowOne: Flow<String>
    lateinit var flowTwo: Flow<String>


    private fun setupFlows() {
        flowOne = flowOf("Юрий", "Александр", "Иван").flowOn(Dispatchers.Default)
        flowTwo = flowOf("Гагарин", "Пушкин", "Грозный").flowOn(Dispatchers.Default)
    }

    private fun zipFlows() {
        CoroutineScope(Dispatchers.Main).launch {
            flowOne.zip(flowTwo)
            { firstString, secondString ->
                "$firstString $secondString"
            }.collect {
                logD(it)
            }
        }
    }

    companion object {
        const val LOG_COROUTINE_TAG = "LOG_COROUTINE_TAG"
    }

    private fun logD(message: Any) {
        Log.d(LOG_COROUTINE_TAG, message.toString())
    }
}
