package com.example.kotlinstandardapplication.Loginpage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.kotlinstandardapplication.Homepage.HomeActivity
import com.example.kotlinstandardapplication.Multipurpose.Multipurpose
import com.example.kotlinstandardapplication.R
import com.example.kotlinstandardapplication.databinding.ActivityLoginBinding

/**
 * @author Phong-Kaster
 * @since 16-02-2023
 * setup component
 */
class LoginActivity : AppCompatActivity() {

    /*use DataBinding*/
    private lateinit var loginBinding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        Multipurpose.hideStatusBar(window)

        setupEvent()
    }

    /**
     * @author Phong-Kaster
     * @since 20-12-2023
     * setup event
     */
    private fun setupEvent()
    {
        loginBinding.buttonLogin.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        loginBinding.buttonLoginGoogle.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}