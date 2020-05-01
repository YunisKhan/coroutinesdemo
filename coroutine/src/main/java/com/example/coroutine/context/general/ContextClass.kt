package com.example.coroutine.context.general

import kotlinx.coroutines.*

class ContextClass {

    fun runDifferentContext() = runBlocking {

        launch {

            println("Main RunBlocking: ${Thread.currentThread().name}")
        }

        launch(Dispatchers.Default) {

            println("Default Dispatcher: ${Thread.currentThread().name}")
        }

        launch(Dispatchers.IO) {

            println("IO Dispatcher: ${Thread.currentThread().name}")
        }

        launch(Dispatchers.Unconfined) {

            println("Unconfined: ${Thread.currentThread().name}")
        }

        launch(newSingleThreadContext("myOwnContext")) {

            println("myOwnContext: ${Thread.currentThread().name}")
        }
    }

    fun runUnconfinedContext() = runBlocking {

        launch(Dispatchers.Unconfined) {

            println("Unconfined: ${Thread.currentThread().name}")
            delay(500L)
            println("Unconfined: ${Thread.currentThread().name}")
        }

        launch {

            println("Run Blocking: ${Thread.currentThread().name}")
            delay(500L)
            println("Run Blocking: ${Thread.currentThread().name}")
        }
    }

    fun runJumpingThreads() {

        newSingleThreadContext("Ctx1").use { ctx1 ->

            newSingleThreadContext("Ctx2").use { ctx2 ->

                runBlocking(ctx1) {

                    println("Running is ${Thread.currentThread().name}")

                    withContext(ctx2) {

                        println("Running is ${Thread.currentThread().name}")
                    }

                    println("Running is ${Thread.currentThread().name}")
                }
            }
        }
    }
}