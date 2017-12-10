package unicauca.movil.beaconsfinalubicuas.di.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
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

}