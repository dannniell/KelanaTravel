package com.example.trevelplannerkelana.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.trevelplannerkelana.databinding.FragmentProfileBinding
import com.example.trevelplannerkelana.ui.login.LoginHostActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var username:String
        var email:String
        var password:String
        val firestore = FirebaseFirestore.getInstance()

        val user = Firebase.auth.currentUser
        val userID = user?.uid

        firestore.collection("users").document(userID.toString()).get()
            .addOnSuccessListener {
                document ->
                username = document.data?.get("username").toString()
                email = document.data?.get("email").toString()
                password = document.data?.get("password").toString()

                binding.tvProfileNama.text = username
                binding.tvMyEmailProfile.text = email
                binding.tvMyPasswordProfile.text = password
            }



        binding.btnLogout.setOnClickListener {
            pindahKeLogin()
        }
    }

    private fun pindahKeLogin() {
        auth = FirebaseAuth.getInstance()
        Firebase.auth.signOut()
        activity?.let {
            val intent = Intent(it, LoginHostActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_TASK_ON_HOME)
            it.startActivity(intent)
        }
    }
}