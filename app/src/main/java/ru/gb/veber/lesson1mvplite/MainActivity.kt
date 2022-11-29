package ru.gb.veber.lesson1mvplite

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val errorHandler = CoroutineExceptionHandler { point1, error ->
        Log.d("WORKER", "null() called with: _ = $point1 error = $error")
    }

    private val coroutineScope = CoroutineScope(Dispatchers.IO + errorHandler)

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

        val myWorkRequest = OneTimeWorkRequestBuilder<MyWorker>().addTag("MyTag").build()
        WorkManager.getInstance(this).enqueue(myWorkRequest)

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(myWorkRequest.id).observe(this) {
            Log.d("WORKER", "onChanged: " + it.state)
        }
        coroutineScope.launch {
            delay(2000)
            WorkManager.getInstance(this@MainActivity).cancelAllWorkByTag("MyTag")
        }
    }
}
