package unicauca.movil.beaconsfinalubicuas.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by Asus on 9/12/2017.
 */

class BeaconReceiver : BroadcastReceiver() {

    private val beacons: PublishSubject<Triple<Int, Int, Int>> = PublishSubject.create()


    override fun onReceive(context: Context?, intent: Intent) {
        val minor = intent.extras.getInt(EXTRA_MINOR)
        val major = intent.extras.getInt(EXTRA_MAJOR)
        val rssi = intent.extras.getInt(EXTRA_RSSI)
        beacons.onNext(Triple(major, minor, rssi))

    }

    fun getBeacons(): Observable<Triple<Int, Int, Int>> = beacons


    companion object {
        val ACTION = "unicauca.movil.beacons.region"
        val EXTRA_MAJOR = "major"
        val EXTRA_MINOR = "minor"
        val EXTRA_RSSI = "rssi"
    }

}