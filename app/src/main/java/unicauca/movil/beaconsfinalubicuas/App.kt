package unicauca.movil.beaconsfinalubicuas

import android.app.Application
import android.content.Intent
import com.estimote.coresdk.observation.region.beacon.BeaconRegion
import com.estimote.coresdk.recognition.packets.Beacon
import com.estimote.coresdk.service.BeaconManager
import unicauca.movil.beaconsfinalubicuas.receivers.BeaconReceiver

/**
 * Created by Asus on 9/12/2017.
 */

class App : Application(), BeaconManager.BeaconRangingListener {

    override fun onCreate() {
        super.onCreate()
        val manager = BeaconManager(this)
        manager.connect {
            manager.startRanging(BeaconRegion("pinturas", null,
                    null, null))
            manager.setRangingListener(this)
        }
    }

    override fun onBeaconsDiscovered(beaconRegion: BeaconRegion?, beacons: MutableList<Beacon>) {
        if(beacons.isNotEmpty()){

            val major = beacons!![0].major
            val minor = beacons[0].minor
            val rssi = beacons[0].rssi

            val intent =  Intent(BeaconReceiver.ACTION)
            intent.putExtra(BeaconReceiver.EXTRA_MAJOR, major)
            intent.putExtra(BeaconReceiver.EXTRA_MINOR, minor)
            intent.putExtra(BeaconReceiver.EXTRA_RSSI, rssi)


            sendBroadcast(intent)
        }
    }

}