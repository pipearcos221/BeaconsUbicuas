package unicauca.movil.beaconsfinalubicuas.ui.game

import android.app.PendingIntent
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.graphics.Color
import android.nfc.NfcAdapter
import android.nfc.NfcManager
import android.nfc.Tag
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.toast
import rx.mqtt.android.RxMqtt
import unicauca.movil.beaconsfinalubicuas.R
import unicauca.movil.beaconsfinalubicuas.di.Injectable
import unicauca.movil.beaconsfinalubicuas.receivers.BeaconReceiver
import unicauca.movil.beaconsfinalubicuas.util.LifeDisposable
import unicauca.movil.beaconsfinalubicuas.util.buildViewModel
import javax.inject.Inject

class GameActivity : AppCompatActivity(), Injectable {


    lateinit var nfcAdapter: NfcAdapter
    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val gameViewModel: GameViewModel by lazy { buildViewModel(factory, GameViewModel::class) }
    private val disposable: LifeDisposable = LifeDisposable(this)
    @Inject
    lateinit var process: DataProcess



    private fun rgbCol(base: String) = "/unicauca/light/$base/color"
    private fun status(base: String) = "/unicauca/light/$base/relay/0"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gameViewModel.setListener()
        gameViewModel.connectSocket()
        val nfcManager: NfcManager
                = getSystemService(Context.NFC_SERVICE) as NfcManager
        nfcAdapter = nfcManager.defaultAdapter



    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(BeaconReceiver.ACTION)
        registerReceiver(gameViewModel.beaconReceiver, filter)

        val intent = Intent(this, GameActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP



        val pendingIntent = PendingIntent.getActivity(this,
                0, intent, 0)

        nfcAdapter.enableForegroundDispatch(this,
                pendingIntent, null, null)


        disposable add gameViewModel.beaconReceiver.getBeacons().subscribe { beacon ->
            if (beacon.second == beacon.second || beacon.third == 0) {
                process.processRssi(beacon.third)
            }
        }

    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val tag: Tag = intent.extras.getParcelable(NfcAdapter.EXTRA_TAG)
        val id = tag.id
        val idHex = byteToStringHex(id)

        Log.i("NFCID", idHex)
        Toast.makeText(this, idHex, Toast.LENGTH_SHORT).show()
        processTag(idHex)

    }

    private fun processTag(idHex: String){
        when(idHex){
            BASE_A -> {
                rxSendStatus("0D","2")
                rxSendColor("0D",gameViewModel.teamColor)
            }
            BASE_B -> {
                rxSendStatus("D0","2")
                rxSendColor("D0",gameViewModel.teamColor)
            }
            else -> {
                toast("Etiqueta no configurada :v")
            }
        }

    }

    private fun byteToStringHex(bytes: ByteArray): String {
        val buffer = StringBuffer()
        bytes.forEach { buffer.append(it.toString(16)) }
        return buffer.toString()
    }

    override fun onPause() {
        super.onPause()
        nfcAdapter.disableForegroundDispatch(this)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(gameViewModel.beaconReceiver)
    }

    fun setColor(red: Int, green: Int, blue: Int) {
        img.backgroundColor = Color.rgb(red, green, blue)
    }

    private fun rxSendColor(base: String, value: String){
        val encodedValue = value.toByteArray(Charsets.UTF_8)
        val msg = MqttMessage(encodedValue)
        RxMqtt.publish(gameViewModel.mqttAndroidClient, "${rgbCol(base)}/set", msg)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe()

    }

    private fun rxSendStatus(base: String, value: String){
        val encodedValue = value.toByteArray(Charsets.UTF_8)
        val msg = MqttMessage(encodedValue)
        RxMqtt.publish(gameViewModel.mqttAndroidClient, "${status(base)}/set", msg)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe()
    }

    companion object {
        private val BASE_A:String = "435-2e42-7b32-80"
        private val BASE_B:String = "467-362a7440-80"
    }
}
