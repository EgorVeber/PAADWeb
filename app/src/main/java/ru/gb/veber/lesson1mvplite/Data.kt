package ru.gb.veber.lesson1mvplite

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.util.concurrent.Flow
import kotlin.random.Random

internal data class Data(val data: String)

internal object DataBase {
    fun fetchData() = Random.nextInt()
}

internal class DataSource(
    private val dataBase: DataBase = DataBase,
    private val refreshIntervalMs: Long = 1000,
) {
    val dataBaseFlow = flow {
        while (true) {
            val dataFromDataBase = dataBase.fetchData()
            emit(dataFromDataBase.toString())
            delay(refreshIntervalMs)
        }
    }.flowOn(Dispatchers.Default).catch { e ->
        println(e.message)
        //Error!
    }
}

internal class Repository(dataSource: DataSource = DataSource()) {
    val userData = dataSource.dataBaseFlow.map { dbFlow -> Data(dbFlow) }
//.onEach { saveInCache(it) }
}

