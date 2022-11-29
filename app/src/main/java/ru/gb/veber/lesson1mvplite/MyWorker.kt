package ru.gb.veber.lesson1mvplite

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Thread.sleep

class MyWorker(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        Log.d("WORKER", "doWork: start")
        try {
            sleep(10000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        Log.d("WORKER", "doWork: end")
        return Result.success()
    }
}

class MyWorker2(context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        try {
            sleep(2000)
            Log.d("WORKER", 2.toString())
        } catch (ex: Exception) {
            return Result.failure(); //или Result.retry()
        }
        return Result.success()
    }
}
