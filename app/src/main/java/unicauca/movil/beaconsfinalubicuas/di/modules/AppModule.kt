package unicauca.movil.beaconsfinalubicuas.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import org.eclipse.paho.android.service.MqttAndroidClient
import rx.mqtt.android.RxMqtt
import unicauca.movil.beaconsfinalubicuas.receivers.BeaconReceiver
import javax.inject.Singleton

/**
 * Created by jlbeltran94 on 10/12/2017.
 */
@Module
class AppModule{

    @Singleton
    @Provides
    fun provideContext(application: Application):Context = application

    @Singleton
    @Provides
    fun providesPreferences(application: Application): SharedPreferences =
            application.getSharedPreferences("BeaconBall", 0)

    @Singleton
    @Provides
    fun providesBeaconreceiver():BeaconReceiver = BeaconReceiver()

    @Singleton
    @Provides
    fun providesMqttAndroidClient(context: Context):MqttAndroidClient =RxMqtt.client(context, "tcp://iot.eclipse.org:1883")

            /*
    * val mqttAndroidCLient: MqttAndroidClient by lazy{
        RxMqtt.client(applicationContext, URL)
    }
    * */
}