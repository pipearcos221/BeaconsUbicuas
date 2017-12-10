package unicauca.movil.beaconsfinalubicuas.di.components

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import unicauca.movil.beaconsfinalubicuas.App
import unicauca.movil.beaconsfinalubicuas.di.modules.ActivityBuilders
import unicauca.movil.beaconsfinalubicuas.di.modules.AppModule
import unicauca.movil.beaconsfinalubicuas.di.modules.NetModule
import javax.inject.Singleton

/**
 * Created by jlbeltran94 on 10/12/2017.
 */
@Singleton
@Component(modules = [(AppModule::class), (NetModule::class), (ActivityBuilders::class)])
interface AppComponent{

    fun inject(app: App)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun applicacion(application: Application):Builder
        fun build():AppComponent
    }

}