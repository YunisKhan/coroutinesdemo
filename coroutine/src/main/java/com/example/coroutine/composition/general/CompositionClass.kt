package com.example.coroutine.composition.general

import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.system.measureTimeMillis

class CompositionClass {

    fun runSequentially() = runBlocking {

        val time = measureTimeMillis {

            val value1 = doSomethingUsefulOne()
            val value2 = doSomethingUsefulTwo()

            println("Result is ${value1 + value2}")
        }

        println("Completed in $time millis")
    }

    fun runConcurrently() = runBlocking {

        val time = measureTimeMillis {

            val value1 = async { doSomethingUsefulOne() }
            val value2 = async { doSomethingUsefulTwo() }

            println("Result is ${value1.await() + value2.await()}")
        }

        println("Completed in $time millis")
    }

    fun runConcurrentlyLazy() = runBlocking {

        val time = measureTimeMillis {

            val value1 = async {
                println("Async value 1")
                doSomethingUsefulOne()
            }
            val value2 = async { doSomethingUsefulTwo() }

            //println("Result is ${value1.await() + value2.await()}")
        }

        println("Completed in $time millis")
    }

    fun runAsyncStyle() = run {
        val time = measureTimeMillis {

            val value1 = doSomethingUsefulOneAsync()
            val value2 = doSomethingUsefulTwoAsync()

            value1.start()
            value2.start()

            runBlocking {

                println("Result is ${value1.await() + value2.await()}")
            }
        }

        println("Completed in $time millis")
    }

    fun runStructuredConcurrency() = runBlocking {
        val time = measureTimeMillis {


            val sum = concurrentSum()

            println("Result is $sum")
        }

        println("Completed in $time millis")
    }

    fun runStructuredConcurrencyFailed() = runBlocking {
        val time = measureTimeMillis {


            try {

                val sum = concurrentSumFailed()
            } catch (e: ArithmeticException) {

                println("Computation failed with ArithmeticException")
            }
        }

        println("Completed in $time millis")
    }

    private suspend fun concurrentSum(): Int = coroutineScope {

        val value1 = async { doSomethingUsefulOne() }
        val value2 = async { doSomethingUsefulTwo() }

        value1.await() + value2.await()
    }

    private suspend fun concurrentSumFailed(): Int = coroutineScope {

        val value1 = async<Int> {

            try {

                delay(Long.MAX_VALUE)
                42
            } finally {

                println("First child was cancelled")
            }
        }
        val value2 = async<Int> {

            println("Second child throws an exception")
            throw ArithmeticException()
        }

        value1.await() + value2.await()
    }

    private fun doSomethingUsefulOneAsync() = GlobalScope.async {

        doSomethingUsefulOne()
    }

    private fun doSomethingUsefulTwoAsync() = GlobalScope.async {

        doSomethingUsefulTwo()
    }

    private suspend fun doSomethingUsefulOne(): Int {

        delay(1000)
        return 13
    }

    private suspend fun doSomethingUsefulTwo(): Int {

        delay(1000)
        return 23
    }
}