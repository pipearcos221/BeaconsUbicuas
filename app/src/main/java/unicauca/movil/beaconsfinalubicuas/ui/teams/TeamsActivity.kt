package unicauca.movil.beaconsfinalubicuas.ui.teams

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_teams.*
import org.jetbrains.anko.startActivity
import unicauca.movil.beaconsfinalubicuas.R
import unicauca.movil.beaconsfinalubicuas.di.Injectable
import unicauca.movil.beaconsfinalubicuas.model.Player
import unicauca.movil.beaconsfinalubicuas.ui.adapters.PlayerAdapter
import unicauca.movil.beaconsfinalubicuas.ui.game.GameActivity
import javax.inject.Inject

class TeamsActivity : AppCompatActivity(), Injectable {

    @Inject
    lateinit var adapterA:PlayerAdapter
    val listA:MutableList<Player> = mutableListOf()
    val listB:MutableList<Player> = mutableListOf()
    val p1:Player = Player("dos","dos","Pipearcos221", "dos")
    val p2:Player = Player("uno","uno","Yizbo", "uno")
    @Inject
    lateinit var adapterB:PlayerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams)
        recA.adapter = adapterA
        recB.adapter = adapterB

        listA.add(p1)
        listB.add(p2)

        adapterA.players = listA
        adapterB.players = listB


        fabStart.clicks().subscribeBy(
                onNext = {
                    startActivity<GameActivity>()
                }
        )
    }
}
