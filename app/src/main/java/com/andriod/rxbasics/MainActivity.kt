package com.andriod.rxbasics

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MyOwnObservable.interval(1000)
            .map {
                it
            }
            .subscribe {
                runOnUiThread {
                    Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
                }
            }


//        Observable.interval(1, TimeUnit.SECONDS).map {
//            it
//        }.observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show() },
//                { err -> Toast.makeText(this, "${err.message}", Toast.LENGTH_SHORT).show() })
//    }

//        val interval = Observable.interval(1, TimeUnit.SECONDS)
//        val map = interval.map { it }
//        map.observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
//            }, {})
    }

    val firstObservable: Observable<String>
        get() = Observable
            .interval(1, TimeUnit.SECONDS)
            .map {
                "$it"
            }

//    fun foo():Observable<String> = Observable//Observable<Int>()
//        .map{
//            "$it"
//        }

    private val myObservable = MyObservable()

    fun foo2() {
        myObservable
            .map {

            }
    }

    fun foo3() {
        Observable
            .interval(1, TimeUnit.SECONDS)
            .subscribe {

            }

        Observable
            .interval(1, TimeUnit.SECONDS)
            .subscribe({

            }, {

            })

        Observable.interval(1, TimeUnit.SECONDS)
            .subscribe({

            }, {

            }, {

            }, {

            })

        Observable.interval(1, TimeUnit.SECONDS)
            .subscribe()
    }
}

class MyObservable : Observable<String>() {
    override fun subscribeActual(observer: Observer<in String>?) {
    }
}