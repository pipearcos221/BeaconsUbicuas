package unicauca.movil.beaconsfinalubicuas.model

/**
 * Created by Asus on 10/12/2017.
 */

class UserLogin (var username:String,var password:String)
class LoginResponse(var success:Boolean, var player: Player)
class RegisterResponse(var success: Boolean, var exist: Boolean)