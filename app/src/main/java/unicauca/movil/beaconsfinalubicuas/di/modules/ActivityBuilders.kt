package unicauca.movil.beaconsfinalubicuas.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import unicauca.movil.beaconsfinalubicuas.ui.login.LoginActivity
import unicauca.movil.beaconsfinalubicuas.ui.game.GameActivity
import unicauca.movil.beaconsfinalubicuas.di.ActivityScope
import unicauca.movil.beaconsfinalubicuas.ui.seletTeam.SelectTeamActivity

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
    abstract fun bindGameActivity(): GameActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun bindSelectTeamActivity(): SelectTeamActivity

}