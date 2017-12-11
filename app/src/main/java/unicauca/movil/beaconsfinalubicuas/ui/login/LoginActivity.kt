package unicauca.movil.beaconsfinalubicuas.ui.login

import android.arch.lifecycle.ViewModelProvider
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.estimote.coresdk.common.requirements.SystemRequirementsChecker
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

import unicauca.movil.beaconsfinalubicuas.R
import unicauca.movil.beaconsfinalubicuas.databinding.ActivityLoginBinding
import unicauca.movil.beaconsfinalubicuas.di.Injectable

import unicauca.movil.beaconsfinalubicuas.model.UserLogin
import unicauca.movil.beaconsfinalubicuas.ui.RegisterActivity
import unicauca.movil.beaconsfinalubicuas.ui.game.GameActivity
import unicauca.movil.beaconsfinalubicuas.ui.seletTeam.SelectTeamActivity
import unicauca.movil.beaconsfinalubicuas.util.buildViewModel
import unicauca.movil.beaconsfinalubicuas.util.validateForm
import javax.inject.Inject


class LoginActivity : AppCompatActivity(), Injectable {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    val viewModel: LoginViewModel by lazy { buildViewModel(factory,LoginViewModel::class) }
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    override fun onResume() {
        super.onResume()
        SystemRequirementsChecker.checkWithDefaultDialogs(this)

        btnLogin.clicks()
                .flatMap { validateForm(R.string.empty_fields,username.editText?.text.toString(),password.editText?.text.toString()) }
                .flatMap { viewModel.Login(UserLogin(it[0],it[1])) }
                .subscribeBy(
                        onNext = {
                            if (it.success){
                                startActivity<SelectTeamActivity>()
                            }else{
                                toast("Usuario o Contraseña incorrecto")
                            }
                        },
                        onError = {
                            toast("error")
                        }
                )
        register.clicks().
                subscribe{startActivity<RegisterActivity>()}
    }
}
