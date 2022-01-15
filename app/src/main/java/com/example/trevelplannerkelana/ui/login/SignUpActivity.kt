package com.example.trevelplannerkelana.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.trevelplannerkelana.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySignUpBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()

        binding.btnSignUpToHome.setOnClickListener {
            signUp()
        }
    }

    private fun signUp() {

        if (binding.tIEnama.text.toString().isEmpty()){
            Toast.makeText(baseContext, "Masukkan Nama", Toast.LENGTH_SHORT).show()
            return
        }
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

        auth.createUserWithEmailAndPassword(binding.tIEEmail.text.toString(), binding.tIEPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser

                    //collection users + nama
                    val userID = user?.uid
                    val userr = hashMapOf(
                        "username" to binding.tIEnama.text.toString(),
                        "email" to binding.tIEEmail.text.toString(),
                        "password" to binding.tIEPassword.text.toString()
                    )
                    if (userID != null) {
                        firestore.collection("users").document(userID)
                            .set(userr)
                    }

                    user!!.sendEmailVerification()
                        .addOnCompleteListener { task ->
                            val loginScreen = Intent(this, LoginHostActivity::class.java)
                            startActivity(loginScreen)
                            finish()
                        }
                } else {
                    Toast.makeText(baseContext, "Register Failed Try Again Later", Toast.LENGTH_SHORT).show()
                }
            }
    }
}