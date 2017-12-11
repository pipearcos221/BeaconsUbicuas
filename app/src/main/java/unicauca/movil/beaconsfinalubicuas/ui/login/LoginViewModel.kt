package unicauca.movil.beaconsfinalubicuas.ui.login

import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import unicauca.movil.beaconsfinalubicuas.model.LoginResponse
import unicauca.movil.beaconsfinalubicuas.model.UserLogin
import unicauca.movil.beaconsfinalubicuas.model.UserSession
import unicauca.movil.beaconsfinalubicuas.net.UserClient
import unicauca.movil.beaconsfinalubicuas.util.applySchedulers
import javax.inject.Inject

/**
 * Created by jlbeltran94 on 10/12/2017.
 */
class LoginViewModel @Inject constructor(val userClient: UserClient,
                                         val session: UserSession): ViewModel(){

    fun Login(userLogin: UserLogin):Observable<LoginResponse> = userClient.login(userLogin)
            .applySchedulers()

}