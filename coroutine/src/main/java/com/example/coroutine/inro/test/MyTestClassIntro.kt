package com.example.coroutine.inro.test

import com.example.coroutine.inro.general.GlobalScopeCoroutine

class MyTestClassIntro {

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {

            testGlobalScopeCoroutine()
        }

        private fun testGlobalScopeCoroutine() {

            val globalScopeCoroutine = GlobalScopeCoroutine()

            globalScopeCoroutine.runGlobalScope()
            globalScopeCoroutine.runGl0balScopeRunBlock()
            globalScopeCoroutine.runGlobalScopeRunBlockingAdapter()
            globalScopeCoroutine.runGlobalScopeRunBlockingAdapterJoin()
            globalScopeCoroutine.runGlobalScopeRunBlockingAdapterWithoutJoin()
            globalScopeCoroutine.runGlobalScopeRunBlockingAndCustomScope()
        }
    }
}