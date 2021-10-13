package com.andriod.rxbasics

import io.reactivex.functions.Function

class MyOwnObservable<T>(private val init: T) {

    var value: T = init
        set(value) {
            run?.invoke(field)
            field = value
        }

    private var run: ((t: T) -> Unit)? = null

    fun <R> map(mapper: Function<in T, out R>): MyOwnObservable<R> {
//        return MyOwnObservable(mapper.apply(value))
        val result = MyOwnObservable(mapper.apply(value))

        subscribe {
            result.value = mapper.apply(this.value)
        }

        return result
    }

    fun subscribe(r: (t: T) -> Unit) {
        run = r
    }

    companion object {
        private val my = MyOwnObservable<Long>(1)

        fun foo() {
            my.map {
                return@map "Hi"
            }.map {

            }
        }

        fun interval(time: Long): MyOwnObservable<Int> {
            var index: Int = 0

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


