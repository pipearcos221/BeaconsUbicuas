package unicauca.movil.beaconsfinalubicuas.di.modules

import android.content.Context
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
    fun provideRetrofit(context: Context): Retrofit = Retrofit.
            Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                    .setDateFormat("dd/MM/yyyy")
                    .create()
            ))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .baseUrl("https://192.168.0.12:3000/")
            .build()

    @Provides
    @Singleton
    fun provideUserClient(retrofit: Retrofit): UserClient =
            retrofit.create(UserClient::class.java)

}