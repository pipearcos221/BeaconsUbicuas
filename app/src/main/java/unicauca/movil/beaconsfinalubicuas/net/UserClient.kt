package unicauca.movil.beaconsfinalubicuas.net

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST
import unicauca.movil.beaconsfinalubicuas.model.LoginResponse
import unicauca.movil.beaconsfinalubicuas.model.Player
import unicauca.movil.beaconsfinalubicuas.model.RegisterResponse
import unicauca.movil.beaconsfinalubicuas.model.UserLogin

/**
 * Created by Asus on 10/12/2017.
 */
interface UserClient {

    @POST("users/login")
    fun login(@Body userLogin: UserLogin): Observable<LoginResponse>

    @POST("users/signin")
    fun signin(@Body player: Player): Observable<RegisterResponse>

}

