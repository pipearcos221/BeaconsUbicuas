package unicauca.movil.beaconsfinalubicuas.ui.game

import android.arch.lifecycle.ViewModel
import android.content.SharedPreferences
import android.graphics.Color
import android.util.Log
import android.widget.Toast
import com.github.nkzawa.socketio.client.Socket
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.eclipse.paho.android.service.MqttAndroidClient
import org.jetbrains.anko.backgroundColor
import unicauca.movil.beaconsfinalubicuas.model.UserSession
import unicauca.movil.beaconsfinalubicuas.receivers.BeaconReceiver
import javax.inject.Inject

/**
 * Created by jlbeltran94 on 10/12/2017.
 */
class GameViewModel @Inject constructor(val beaconReceiver: BeaconReceiver,val user: UserSession, val socket: Socket, val mqttAndroidClient: MqttAndroidClient):ViewModel(){

    val baseMinor = user.teamId
    val teamColor = user.teamColor

    fun connectSocket() {
        socket.connect()
    }
    fun setListener(){
        socket.on("player_registered", { data ->
            run {
                async(UI) {
//                    txt.text = data.first().toString()
                    Log.i("socket123", data.first().toString())
                }
            }
        })
        socket.on("player_registered", { data ->
            run {
                async(UI) {
                    //                    txt.text = data.first().toString()
                    Log.i("socket123", data.first().toString())
                }
            }
        })

    }





}