package com.example.coroutine.composition.test

import com.example.coroutine.composition.general.CompositionClass

class MyTestClassComposition {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {

            val composition = CompositionClass()

            composition.runConcurrentlyLazy()
            /*composition.runConcurrently()
            composition.runAsyncStyle()
            composition.runStructuredConcurrency()
            composition.runStructuredConcurrencyFailed()*/
        }
    }
}