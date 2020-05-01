package com.example.coroutine.cancellation.general

import kotlinx.coroutines.*

class CancellationClass {

    fun cancelCoroutine() = runBlocking {

        val job = launch {

            repeat(1000) { index ->

                println("Job: I'm sleeping $index..")
                delay(500L)
            }
        }

        delay(1200L)
        println("CancelCoroutine: I'm tired of waiting")

        job.cancel()
        job.join()
        println("main: Now I can quit.")
    }

    fun cancelCoroutineNonCooprative() = runBlocking {

        val startTime = System.currentTimeMillis()

        val job = launch(Dispatchers.Default) {

            var nextPrintTime = startTime
            var i = 0
            while (i < 5) { // computation loop, just wastes CPU
                // print a message twice a second
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("job: I'm sleeping ${i++} ...")
                    nextPrintTime += 500L
                }
            }
        }

        delay(1200L)
        println("CancelCoroutine: I'm tired of waiting")

        job.cancel()
        job.join()
        println("main: Now I can quit.")
    }

    fun cancelCoroutineCooprative() = runBlocking {

        val startTime = System.currentTimeMillis()

        val job = launch(Dispatchers.Default) {

            var nextPrintTime = startTime
            var i = 0
            while (isActive) { // computation loop, just wastes CPU
                // print a message twice a second
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("job: I'm sleeping ${i++} ...")
                    nextPrintTime += 500L
                }
            }
        }

        delay(1200L)
        println("CancelCoroutine: I'm tired of waiting")

        job.cancel()
        job.join()
        println("main: Now I can quit.")
    }

    fun cancelCoroutineFinally() = runBlocking {

        val job = launch {

            try {

                repeat(1000) { index ->

                    println("Job: I'm sleeping $index..")
                    delay(500L)
                }
            } finally {

                println("job: I'm running finally")
            }
        }

        delay(1200L)
        println("CancelCoroutine: I'm tired of waiting")

        job.cancel()
        job.join()
        println("main: Now I can quit.")
    }

    fun cancelCoroutineNonCancellable() = runBlocking {

        val job = launch {

            try {

                repeat(1000) { index ->

                    println("Job: I'm sleeping $index..")
                    delay(500L)
                }
            } finally {

                withContext(NonCancellable) {

                    println("job: I'm running finally")
                    delay(1000)
                    println("job: And I've just delayed for 1 sec because I'm non-cancellable")
                }
            }
        }

        delay(1200L)
        println("CancelCoroutine: I'm tired of waiting")

        job.cancel()
        job.join()
        println("main: Now I can quit.")
    }

    fun cancelCoroutineTimeOut() = runBlocking {

        withTimeout(1300) {

            launch {

                repeat(1000) { index ->

                    println("Job: I'm sleeping $index..")
                    delay(500L)
                }
            }
        }
    }

    fun cancelCoroutineTimeOutOrNull() = runBlocking {

        val result = withTimeoutOrNull(1300) {

            launch {

                repeat(1000) { index ->

                    println("Job: I'm sleeping $index..")
                    delay(500L)
                }
            }
            "Done"
        }

        println("Result is $result")
    }
}