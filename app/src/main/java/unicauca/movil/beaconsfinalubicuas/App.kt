package unicauca.movil.beaconsfinalubicuas

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import com.estimote.coresdk.observation.region.beacon.BeaconRegion
import com.estimote.coresdk.recognition.packets.Beacon
import com.estimote.coresdk.service.BeaconManager
import dagger.android.DispatchingAndroidInjector
import unicauca.movil.beaconsfinalubicuas.receivers.BeaconReceiver
import javax.inject.Inject

/**
 * Created by Asus on 9/12/2017.
 */

class App : Application(), BeaconManager.BeaconRangingListener, BeaconManager.BeaconMonitoringListener {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        val manager = BeaconManager(this)
        manager.connect {
            manager.startMonitoring(BeaconRegion("pinturas", null,
                    null, null))
            manager.startRanging(BeaconRegion("pinturas", null,
                    null, null))
            manager.setRangingListener(this)
            manager.setMonitoringListener(this)

        }
    }

    override fun onBeaconsDiscovered(beaconRegion: BeaconRegion?, beacons: MutableList<Beacon>) {
        if(beacons.isNotEmpty()){
            val major = beacons.first().major
            val minor = beacons.first().minor
            val rssi = beacons.first().rssi

            val intent =  Intent(BeaconReceiver.ACTION)
            intent.putExtra(BeaconReceiver.EXTRA_MAJOR, major)
            intent.putExtra(BeaconReceiver.EXTRA_MINOR, minor)
            intent.putExtra(BeaconReceiver.EXTRA_RSSI, rssi)

            sendBroadcast(intent)
        }

    }

    override fun onExitedRegion(beaconRegion: BeaconRegion?) {
        val intent = Intent(BeaconReceiver.ACTION)
        intent.putExtra(BeaconReceiver.EXTRA_MAJOR, 0)
        intent.putExtra(BeaconReceiver.EXTRA_MINOR, 0)
        intent.putExtra(BeaconReceiver.EXTRA_RSSI, 0)

        sendBroadcast(intent)
    }

    override fun onEnteredRegion(beaconRegion: BeaconRegion?, beacons: MutableList<Beacon>) {
        if(beacons.isNotEmpty()) {
            val major = beacons.first().major
            val minor = beacons.first().minor
            val rssi = beacons.first().rssi

            val intent = Intent(BeaconReceiver.ACTION)
            intent.putExtra(BeaconReceiver.EXTRA_MAJOR, major)
            intent.putExtra(BeaconReceiver.EXTRA_MINOR, minor)
            intent.putExtra(BeaconReceiver.EXTRA_RSSI, rssi)

            sendBroadcast(intent)
        }
    }



}