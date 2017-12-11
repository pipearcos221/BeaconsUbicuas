package unicauca.movil.beaconsfinalubicuas.di.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import unicauca.movil.beaconsfinalubicuas.ui.game.GameViewModel
import unicauca.movil.beaconsfinalubicuas.ui.login.LoginViewModel
import unicauca.movil.beaconsfinalubicuas.ui.seletTeam.SelectTeamViewModel
import unicauca.movil.beaconsfinalubicuas.ui.teams.TeamsViewModel
import unicauca.movil.beaconsfinalubicuas.util.AppViewModelFactory
import kotlin.reflect.KClass

/**
 * Created by jlbeltran94 on 10/12/2017.
 */

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule{

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GameViewModel::class)
    abstract fun bindGameViewModel(viewModel: GameViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SelectTeamViewModel::class)
    abstract fun bindSelectTeamViewModel(viewModel: SelectTeamViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TeamsViewModel::class)
    abstract fun bindTeamsViewModel(viewModel: TeamsViewModel): ViewModel

}