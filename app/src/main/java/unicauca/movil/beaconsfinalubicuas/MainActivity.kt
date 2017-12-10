package unicauca.movil.beaconsfinalubicuas

import android.content.IntentFilter
import android.content.SharedPreferences
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.estimote.coresdk.common.requirements.SystemRequirementsChecker
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.backgroundColor
import unicauca.movil.beaconsfinalubicuas.receivers.BeaconReceiver
import unicauca.movil.beaconsfinalubicuas.util.add

class MainActivity : AppCompatActivity() {

    private val beaconReceiver: BeaconReceiver = BeaconReceiver()
    private var disposable: CompositeDisposable = CompositeDisposable()
    lateinit var prefs: SharedPreferences
    private var baseMinor: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SystemRequirementsChecker.checkWithDefaultDialogs(this)
        prefs = applicationContext.getSharedPreferences("beacon", 0)
        baseMinor = prefs.getInt("baseMinor", 0)
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(BeaconReceiver.ACTION)
        registerReceiver(beaconReceiver, filter)

        disposable add beaconReceiver.getBeacons()
                .subscribe { beacon ->
                    if (beacon.second == beacon.second || beacon.third == 0) {
                        Log.i("beacons123", "major : ${beacon.first} minor: ${beacon.second} rssi: ${beacon.third}")
                        processRssi(beacon.third)
                        Toast.makeText(this, beacon.third.toString(), Toast.LENGTH_SHORT).show()


                    }
//                    else if (beacon.third == 0){
//                        Log.i("beacons123", "Salio de la region")
//                        Toast.makeText(this, "Salio de la region", Toast.LENGTH_SHORT).show()
//                        processRssi(beacon.third)
//                    }
                }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(beaconReceiver)
        disposable.clear()
    }

    fun processRssi(rssi: Int) {
        when (rssi) {
            in -75..-40 -> img.backgroundColor = Color.rgb(0, 255, 0)
            in -95..-75 -> img.backgroundColor = Color.rgb(255, 156, 35)
            else -> img.backgroundColor = Color.rgb(255, 0, 0)

        }
    }

}
