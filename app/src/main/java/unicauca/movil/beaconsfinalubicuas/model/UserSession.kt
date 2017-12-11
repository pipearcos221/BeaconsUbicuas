package unicauca.movil.beaconsfinalubicuas.model

import android.content.SharedPreferences
import unicauca.movil.beaconsfinalubicuas.util.save
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by jlbeltran94 on 10/12/2017.
 */
@Singleton
class UserSession @Inject constructor(val prefs: SharedPreferences){

   var userId: String
        get() = prefs.getString(KEY_USERID, "")
        set(value) = prefs.save(KEY_USERID to value)

    var teamId: String
        get() = prefs.getString(KEY_TEAM_ID, "")
        set(value) = prefs.save(KEY_TEAM_ID to value)

    var teamColor: String
        get() = prefs.getString(KEY_FARM_ID, "255,0,0")
        set(value) = prefs.save(KEY_FARM_ID to value)



    companion object {
        private val KEY_USERID = "userId"
        private val KEY_TEAM_ID = "farm"
        private val KEY_FARM_ID = "farmID"
    }

}