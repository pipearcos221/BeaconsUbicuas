package unicauca.movil.beaconsfinalubicuas.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.SharedPreferences
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.toast
import kotlin.reflect.KClass

/**
 * Created by Asus on 9/12/2017.
 */

infix fun CompositeDisposable.add(disposable: Disposable) {
    this.add(disposable)
}

fun <T : ViewModel> AppCompatActivity.buildViewModel(factory: ViewModelProvider.Factory, kClass: KClass<T>): T
        = ViewModelProviders.of(this, factory).get(kClass.java)

fun <T : ViewModel> Fragment.buildViewModel(factory: ViewModelProvider.Factory, kClass: KClass<T>): T
        = ViewModelProviders.of(this, factory).get(kClass.java)

fun ViewGroup.inflate(layout: Int) = LayoutInflater.from(context).inflate(layout, this, false)

fun SharedPreferences.save(vararg data: Pair<String, Any>) {
    val editor = edit()
    data.forEach { (key, value) ->
        when (value) {
            is String -> editor.putString(key, value)
            is Int -> editor.putInt(key, value)
            is Boolean -> editor.putBoolean(key, value)
            is Long -> editor.putLong(key, value)
        }

    }
    editor.apply()
}

fun AppCompatActivity.validateForm(message: Int,
                                   vararg fields: String): Observable<List<String>>
        = Observable.create<List<String>> {
    if (fields.contains("")) toast(message)
    else it.onNext(fields.toList())
    it.onComplete()
}