package unicauca.movil.beaconsfinalubicuas.di.modules

import android.content.Context
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import unicauca.movil.beaconsfinalubicuas.model.LoginResponse
import unicauca.movil.beaconsfinalubicuas.model.UserLogin
import unicauca.movil.beaconsfinalubicuas.net.UserClient
import javax.inject.Singleton

/**
 * Created by jlbeltran94 on 10/12/2017.
 */
@Module
class NetModule{
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.
            Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                    .setDateFormat("dd/MM/yyyy")
                    .create()
            ))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .baseUrl("http://192.168.43.47:3000/")
            .build()

    @Provides
    @Singleton
    fun provideSocket(): Socket = IO.socket("http://192.168.43.47:3000")

    @Provides
    @Singleton
    fun provideUserClient(retrofit: Retrofit): UserClient =
            retrofit.create(UserClient::class.java)

}