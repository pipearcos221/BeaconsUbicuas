package unicauca.movil.beaconsfinalubicuas

import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val beaconReceiver:BeaconReceiver = BeaconReceiver()
    lateinit var disposable:Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SystemRequirementsChecker.checkWithDefaultDialogs(this)
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(BeaconReceiver.ACTION)
        registerReceiver(beaconReceiver, filter)

        disposable = beaconReceiver.getBeacons()
                .subscribe { beacon->
                    Log.i("beacons123","major : ${beacon.first} minor: ${beacon.second} rssi: ${beacon.third}")
                    Toast.makeText(this, beacon.third.toString(), Toast.LENGTH_SHORT).show()
                }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(beaconReceiver)
        disposable.dispose()
    }

}
