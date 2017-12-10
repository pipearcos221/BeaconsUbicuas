package unicauca.movil.beaconsfinalubicuas

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import unicauca.movil.beaconsfinalubicuas.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    override fun onResume() {
        super.onResume()

        btnLogin.clicks()
                .subscribe{startActivity<MainActivity>()}
    }
}
