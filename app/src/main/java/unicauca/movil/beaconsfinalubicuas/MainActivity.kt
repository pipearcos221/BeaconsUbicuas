package unicauca.movil.beaconsfinalubicuas

import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.estimote.coresdk.common.requirements.SystemRequirementsChecker
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import unicauca.movil.beaconsfinalubicuas.receivers.BeaconReceiver
import unicauca.movil.beaconsfinalubicuas.util.add

class MainActivity : AppCompatActivity() {

    private val beaconReceiver: BeaconReceiver = BeaconReceiver()
    var disposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SystemRequirementsChecker.checkWithDefaultDialogs(this)
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(BeaconReceiver.ACTION)
        registerReceiver(beaconReceiver, filter)

        disposable add beaconReceiver.getBeacons()
                .subscribe { beacon ->
                    Log.i("beacons123", "major : ${beacon.first} minor: ${beacon.second} rssi: ${beacon.third}")
                    Toast.makeText(this, beacon.third.toString(), Toast.LENGTH_SHORT).show()
                }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(beaconReceiver)
        disposable.clear()
    }

}
