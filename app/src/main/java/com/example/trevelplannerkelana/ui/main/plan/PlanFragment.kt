package com.example.trevelplannerkelana.ui.main.plan

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trevelplannerkelana.adapter.PlanAdapter
import com.example.trevelplannerkelana.data.Plan
import com.example.trevelplannerkelana.databinding.FragmentPlanBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class PlanFragment : Fragment() {
    private var _binding: FragmentPlanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPlanBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firestore = FirebaseFirestore.getInstance()

        val user = Firebase.auth.currentUser
        val userID = user?.uid

        var dataPlanCard: ArrayList<Plan>

        //url shortpath
        var direct =  arrayListOf("https://www.google.com/maps/dir/My+Location")
      //  direct = "https://www.google.com/maps/dir/My+Location"


        var email:String
        firestore.collection("users").document(userID.toString()).get()
            .addOnSuccessListener { document ->
                email = document.data?.get("email").toString()

                firestore.collection("users").document(email).collection("plan").get()
                    .addOnSuccessListener {
                            result ->
                        dataPlanCard = ArrayList()
                        for (document in result){
                            //direct!!.add(
                             //   document.data["map"].toString()
                            //)
                            //url + all plan
                            var dir: String
                            dir = document.data["map"].toString()
                            direct.add(dir)
                            //
                            dataPlanCard!!.add(
                                Plan(
                                    document.data["img"].toString(),
                                    document.data["name"].toString(),
                                    document.data["code"].toString(),
                                    document.data["collectionData"].toString()
                                )
                            )
                        }
                        binding.recyclePlan.layoutManager = LinearLayoutManager(activity)
                        binding.recyclePlan.adapter = PlanAdapter(dataPlanCard!!)


                        //Log.d(tag, message)

                        var datamap = direct.joinToString(separator = "")
                       // binding.btnShortPath.text = datamap


                        //button direction intent google maps
                        binding.btnShortPath.setOnClickListener {
                            // Create a Uri from an intent string. Use the result to create an Intent.
                            val gmmIntentUri = Uri.parse(datamap)

                            // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                            // Make the Intent explicit by setting the Google Maps package
                            mapIntent.setPackage("com.google.android.apps.maps")

                            // Attempt to start an activity that can handle the Intent
                            startActivity(mapIntent)
                        }
                    }

            }

    }
}