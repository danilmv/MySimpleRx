package com.andriod.rxbasics

import io.reactivex.functions.Function

class MyOwnObservable<T>(init: T) {

    private var value: T = init
        set(value) {
            field = value
            run?.invoke(field)
        }

    private var run: ((t: T) -> Unit)? = null

    fun <R> map(mapper: Function<in T, out R>) =
        MyOwnObservable<R>(mapper.apply(value)).also { new ->
            subscribe {
                new.value = mapper.apply( value )
            }
        }

    fun subscribe(r: (t: T) -> Unit) {
        run = r
    }

    companion object {
        fun interval(time: Long): MyOwnObservable<Int> {
            var index = 0
            val result = MyOwnObservable(index)

            Thread {
                do {
                    Thread.sleep(time)
                    result.value = ++index
                } while (true)
            }.apply {
                isDaemon = true
                start()
            }

            return result
        }
    }
}


