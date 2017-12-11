package unicauca.movil.beaconsfinalubicuas.ui


import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast
import unicauca.movil.beaconsfinalubicuas.R
import unicauca.movil.beaconsfinalubicuas.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Registro"
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

    }

    override fun onResume() {
        super.onResume()
        button.clicks().subscribeBy(
                onNext = {
                    toast("Usuario registrado correctamente")
                    finish()
                }
        )

    }
}
