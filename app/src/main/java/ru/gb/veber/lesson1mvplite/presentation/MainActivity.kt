package ru.gb.veber.lesson1mvplite.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.gb.veber.lesson1mvplite.R
import ru.gb.veber.lesson1mvplite.presenter.Presenter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Presenter().getData()
    }
}