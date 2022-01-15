package com.example.trevelplannerkelana.ui.main.plan

import android.os.Bundle
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

        var email:String
        firestore.collection("users").document(userID.toString()).get()
            .addOnSuccessListener { document ->
                email = document.data?.get("email").toString()

                firestore.collection("users").document(email).collection("plan").get()
                    .addOnSuccessListener {
                            result ->
                        dataPlanCard = ArrayList()
                        for (document in result){
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

                    }

            }




    }
}