package com.example.fetchrewards.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
): T {
    var data: T? = null
    val latch = CountDownLatch(1)

    val observer = Observer<T> { value ->
        data = value
        latch.countDown()
    }

    this.observeForever(observer)

    latch.await(time, timeUnit)

    this.removeObserver(observer)

    return data ?: throw IllegalStateException("LiveData value was not set")
}