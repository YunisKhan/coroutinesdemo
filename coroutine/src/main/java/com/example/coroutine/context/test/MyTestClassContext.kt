package com.example.coroutine.context.test

import com.example.coroutine.context.general.ContextClass

class MyTestClassContext {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {

            val context = ContextClass()

            //context.runDifferentContext()
            //context.runUnconfinedContext()
            context.runJumpingThreads()
        }
    }
}