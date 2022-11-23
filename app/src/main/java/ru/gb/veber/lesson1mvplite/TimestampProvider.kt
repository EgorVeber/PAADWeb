package ru.gb.veber.lesson1mvplite

interface TimestampProvider {
    fun getMilliseconds(): Long
}