package com.example.coroutine.inro.general

import kotlinx.coroutines.*

class GlobalScopeCoroutine {

    companion object {

        private const val TAG: String = "GlobalScopeCoroutine"
    }

    fun runGlobalScope() {

        GlobalScope.launch {

            delay(1000)
            print("\'s method called")
        }

        print(TAG)
        Thread.sleep(2000)
    }

    fun runGl0balScopeRunBlock() {

        GlobalScope.launch {

            delay(1000)
            print("\'s method called: RunBlock")
        }

        print("\n" + TAG)

        runBlocking {

            delay(2000)
        }
    }

    fun runGlobalScopeRunBlockingAdapter() = runBlocking {

        GlobalScope.launch {

            delay(1000)
            print("\'s method called: RunBlockingAdapter")
        }

        print("\n" + TAG)
        delay(2000)
    }

    fun runGlobalScopeRunBlockingAdapterJoin() = runBlocking {

        val job = GlobalScope.launch {

            delay(1000)
            print("\'s method called: RunBlockingAdapterJoin")
        }

        print("\n" + TAG)
        job.join()
    }

    fun runGlobalScopeRunBlockingAdapterWithoutJoin() = runBlocking {

        launch {

            delay(1000)
            print("\'s method called: RunBlockingAdapterWithoutJoin")
        }

        print("\n" + TAG)
    }

    fun runGlobalScopeRunBlockingAndCustomScope() = runBlocking {

        print("\n**********************\n")
        launch {

            delay(200L)
            println("Task launch run blocking")
        }

        coroutineScope {

            launch {

                delay(500L)
                println("Task launch nested run blocking")
            }

            delay(100L)
            println("Task coroutine scope")
        }

        println("Coroutine scope over")
        println("*******************\n")
    }
}