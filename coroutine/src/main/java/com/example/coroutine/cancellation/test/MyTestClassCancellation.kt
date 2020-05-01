package com.example.coroutine.cancellation.test

import com.example.coroutine.cancellation.general.CancellationClass

class MyTestClassCancellation {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {

            val cancellationClass = CancellationClass()

            cancellationClass.cancelCoroutine()
            cancellationClass.cancelCoroutineNonCooprative()
            cancellationClass.cancelCoroutineCooprative()
            cancellationClass.cancelCoroutineFinally()
            cancellationClass.cancelCoroutineNonCancellable()
            //cancellationClass.cancelCoroutineTimeOut()
            cancellationClass.cancelCoroutineTimeOutOrNull()
        }
    }
}