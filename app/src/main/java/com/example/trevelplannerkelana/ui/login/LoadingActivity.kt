package com.example.trevelplannerkelana.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.trevelplannerkelana.R
import com.example.trevelplannerkelana.databinding.ActivityLoadingBinding


class LoadingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoadingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        //view binding
        binding = ActivityLoadingBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_loading)
        setContentView(binding.root)

        supportActionBar?.hide()

        // logo kelana blm muncul
        val imgres = R.drawable.img_loading_screen
        binding.ivLogoLoading.setImageResource(imgres)

        binding.btnMulai.setOnClickListener {
            val loginScreen = Intent(this, LoginHostActivity::class.java)
            startActivity(loginScreen)
            finish()
        }
    }
}