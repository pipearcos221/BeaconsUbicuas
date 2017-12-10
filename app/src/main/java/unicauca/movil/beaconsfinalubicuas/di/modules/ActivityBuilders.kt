package unicauca.movil.beaconsfinalubicuas.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import unicauca.movil.beaconsfinalubicuas.ui.LoginActivity
import unicauca.movil.beaconsfinalubicuas.ui.MainActivity
import unicauca.movil.beaconsfinalubicuas.di.ActivityScope

/**
 * Created by jlbeltran94 on 10/12/2017.
 */
@Module
abstract class ActivityBuilders{

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindLoginActivity(): LoginActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

}