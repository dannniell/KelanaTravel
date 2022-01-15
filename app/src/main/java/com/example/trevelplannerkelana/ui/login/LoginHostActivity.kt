package com.example.trevelplannerkelana.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trevelplannerkelana.databinding.ActivityLoginHostBinding



class LoginHostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginHostBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginHostBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        // set toolbar as support action bar
        supportActionBar?.hide()

        binding.btnLogin.setOnClickListener {
            val signIn = Intent(this, SignInActivity::class.java)
            startActivity(signIn)
        }
        binding.btnRegistration.setOnClickListener {
            val signUp = Intent(this, SignUpActivity::class.java)
            startActivity(signUp)
        }
    }
}