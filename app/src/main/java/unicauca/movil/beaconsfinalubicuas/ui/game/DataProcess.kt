package unicauca.movil.beaconsfinalubicuas.ui.game

import unicauca.movil.beaconsfinalubicuas.di.ActivityScope
import javax.inject.Inject

/**
 * Created by jlbeltran94 on 10/12/2017.
 */
@ActivityScope
class DataProcess @Inject constructor(val activity: GameActivity){
    fun processRssi(rssi: Int) {
        when (rssi) {
            in -75..-40 -> activity.setColor(0,255,0)
            in -95..-75 -> activity.setColor(255, 156, 35)
            else -> activity.setColor(255, 156, 35)

        }
    }


}