package unicauca.movil.beaconsfinalubicuas.ui.seletTeam

import android.arch.lifecycle.ViewModelProvider
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_select_team.*
import org.jetbrains.anko.startActivity
import unicauca.movil.beaconsfinalubicuas.R
import unicauca.movil.beaconsfinalubicuas.di.Injectable
import unicauca.movil.beaconsfinalubicuas.ui.teams.TeamsActivity
import unicauca.movil.beaconsfinalubicuas.util.LifeDisposable
import unicauca.movil.beaconsfinalubicuas.util.buildViewModel
import javax.inject.Inject

class SelectTeamActivity : AppCompatActivity(),Injectable {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val selectTeamViewModel: SelectTeamViewModel by lazy { buildViewModel(factory, SelectTeamViewModel::class) }
    private val disposable: LifeDisposable = LifeDisposable(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_team)

        red.clicks().subscribeBy(
                onNext = {
                    startActivity<TeamsActivity>()
                }
        )
        blue.clicks().subscribeBy(
                onNext = {
                    startActivity<TeamsActivity>()
                }
        )



    }


}
