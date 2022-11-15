package ru.gb.veber.lesson1mvplite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val shop = Shop()

        var customer1 = object :Customer{
            override fun onNewArrival(arrival: String) {
                println("VVV $arrival received1")
            }
        }
        var customer2 = object :Customer{
            override fun onNewArrival(arrival: String) {
                println("VVV $arrival received2")
            }
        }
        var customer3 = object :Customer{
            override fun onNewArrival(arrival: String) {
                println("VVV $arrival received3")
            }
        }

        shop.subscribe(customer2)
        shop.subscribe(customer1)
        shop.subscribe(customer3)
        shop.notifyOnNewArrival("VVV Some arrival")
    }
}