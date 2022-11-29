package ru.gb.veber.lesson1mvplite

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val myWorkRequest = OneTimeWorkRequestBuilder<MyWorker>().build()
//        val myWorkRequest2 = OneTimeWorkRequestBuilder<MyWorker2>().build()
//
//      val x=  WorkManager.getInstance(this)
//            .beginWith(myWorkRequest)
//            .then(myWorkRequest2)
//            .enqueue()

        val myWorkRequest = OneTimeWorkRequestBuilder<MyWorker>().build()
        WorkManager.getInstance(this).enqueue(myWorkRequest)

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(myWorkRequest.id).observe(this) {
            Log.d("WORKER", "onChanged: " + it.state)
        }


        WorkManager.getInstance(this).cancelAllWork()
    }
}
