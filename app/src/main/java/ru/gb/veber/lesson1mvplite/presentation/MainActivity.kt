package ru.gb.veber.lesson1mvplite.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.gb.veber.lesson1mvplite.App
import ru.gb.veber.lesson1mvplite.databinding.ActivityMainBinding
import ru.gb.veber.lesson1mvplite.entities.Data

class MainActivity : AppCompatActivity(), MainActivityView {
    private lateinit var binding: ActivityMainBinding
    private val presenter = App.instance.presenterImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.attach(this)

        binding.button.setOnClickListener {
            presenter.getData()
        }
    }

    override fun showData(data: Data) {
        binding.textView.text = data.string
    }

    override fun onStop() {
        super.onStop()
        presenter.detach()
    }
}