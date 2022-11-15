package ru.gb.veber.lesson1mvplite.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import ru.gb.veber.lesson1mvplite.databinding.ActivityMainBinding
import ru.gb.veber.lesson1mvplite.presentation.viewmodel.MainActivityViewModel
import ru.gb.veber.lesson1mvplite.presentation.viewmodel.MainActivityViewModelImpl

class MainActivity : AppCompatActivity() {

    private val viewModel:MainActivityViewModel.ViewModel by lazy {
        ViewModelProvider(this).get(MainActivityViewModelImpl::class.java)
        //  ViewModelProvider.NewInstanceFactory().create(MainActivityViewModelImpl::class.java) не отслеживается ЖЦ
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            viewModel.loadData()
        }
        viewModel.data.observe(this) {
            binding.text.text = it
        }
    }
}