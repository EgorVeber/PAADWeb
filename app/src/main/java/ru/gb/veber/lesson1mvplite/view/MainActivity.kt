package ru.gb.veber.lesson1mvplite.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.gb.veber.lesson1mvplite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val model by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        model.subscribe().observe(this) {
            renderDate(it)
        }

        binding.buttonStart.setOnClickListener {
            model.start()
        }

        binding.buttonPause.setOnClickListener {
            model.pause()

        }

        binding.buttonStop.setOnClickListener {
            model.stop()
        }
    }

    private fun renderDate(it: String) {
        binding.textTime.text = it
    }

    companion object {
        const val LOG_COROUTINE_TAG = "LOG_COROUTINE_TAG"
    }
}
