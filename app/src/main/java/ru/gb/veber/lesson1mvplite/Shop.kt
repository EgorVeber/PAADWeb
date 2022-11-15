package ru.gb.veber.lesson1mvplite

class Shop {

    var list = mutableListOf<Customer>()

    fun subscribe(customer: Customer) {
        list.add(customer)
    }

    fun unSubscribe(customer: Customer) {
        list.remove(customer)
    }

    fun notifyOnNewArrival(arrival:String) {
        list.forEach {
            it.onNewArrival(arrival)
        }
    }
}