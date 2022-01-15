package com.example.trevelplannerkelana.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.trevelplannerkelana.databinding.ActivitySignInBinding
import com.example.trevelplannerkelana.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.ArrayList

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var auth : FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignInBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()
        auth = FirebaseAuth.getInstance()

        binding.btnLoginToHome.setOnClickListener {
            login()
        }
    }

    private fun login() {
        if (binding.tIEEmail.text.toString().isEmpty()){
            Toast.makeText(baseContext, "Masukkan Email", Toast.LENGTH_SHORT).show()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(binding.tIEEmail.text.toString()).matches()){
            Toast.makeText(baseContext, "Email Tidak Valid", Toast.LENGTH_SHORT).show()
            return
        }
        if (binding.tIEPassword.text.toString().isEmpty()){
            Toast.makeText(baseContext, "Masukkan Password", Toast.LENGTH_SHORT).show()
            return
        }

        auth.signInWithEmailAndPassword(binding.tIEEmail.text.toString(), binding.tIEPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser

                    updateUI(user)

                } else {
                    // If sign in fails, display a message to the user.
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user !=null){
            if (user.isEmailVerified){
                val main = Intent(this, MainActivity::class.java)
                startActivity(main)
                finish()
            } else{
                Toast.makeText(baseContext, "Verified Email", Toast.LENGTH_SHORT).show()
            }
        } else{
            Toast.makeText(baseContext, "Login Failed Try Again Later", Toast.LENGTH_SHORT).show()
        }
    }
}