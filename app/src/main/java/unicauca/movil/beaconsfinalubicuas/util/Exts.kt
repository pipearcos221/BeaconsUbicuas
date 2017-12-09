package unicauca.movil.beaconsfinalubicuas.util

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Asus on 9/12/2017.
 */

infix fun CompositeDisposable.add(disposable: Disposable) {
    this.add(disposable)
}