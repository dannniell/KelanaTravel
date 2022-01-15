package com.example.trevelplannerkelana.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trevelplannerkelana.adapter.DetailImgAdapter
import com.example.trevelplannerkelana.data.DetailDoc
import com.example.trevelplannerkelana.data.DetailImg
import com.example.trevelplannerkelana.databinding.FragmentDetailBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    lateinit var codeDetail: String
    lateinit var collectionDetaill: String
    lateinit var nama: String
    lateinit var foto: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firestore = FirebaseFirestore.getInstance()

        collectionDetaill = arguments?.getString("c1").toString()
        codeDetail = arguments?.getString("d1").toString()
        nama = arguments?.getString("nama").toString()
        foto = arguments?.getString("foto").toString()

        var dataDetailImg: ArrayList<DetailImg>?

        //recycle view img
        firestore.collection(collectionDetaill).document(codeDetail).collection("IMG").get()
            .addOnSuccessListener { result ->
                dataDetailImg = ArrayList()
                for (document in result) {
                    dataDetailImg!!.add(
                        DetailImg(
                            document.data["img"].toString()
                        )
                    )
                }
                binding.detailImageRecycle.layoutManager =
                    LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                binding.detailImageRecycle.adapter = DetailImgAdapter(dataDetailImg!!)

            }

        //data lainnya
        var judul:String
        var loc:String
        var des:String
        firestore.collection(collectionDetaill).document(codeDetail).collection("DESC").document("desc").get()
            .addOnSuccessListener { document ->

                judul = document.data?.get("judul").toString()
                loc = document.data?.get("loc").toString()
                des = document.data?.get("des").toString()

                binding.tvSublocationDetail.text = judul
                binding.tvLocationDetail.text = loc
                binding.tvDescripDetail.text = des

            }

        // add plan
        val user = Firebase.auth.currentUser
        val userID = user?.uid



        binding.btnAddPlan.setOnClickListener {
            //benerin ini dulu bre pharsing email biar bisa sama web
            var email:String
            firestore.collection("users").document(userID.toString()).get()
                .addOnSuccessListener { document ->
                    email = document.data?.get("email").toString()

                    val addPlanData = firestore.collection("users").document(email).collection("plan").document(codeDetail)
                    addPlanData.get().addOnSuccessListener {
                            docSnapshot ->
                        if (docSnapshot.exists()){
                            Toast.makeText(activity, "Sudah Ditambahkan", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            val setPlan = hashMapOf(
                                "code" to codeDetail,
                                "collectionData" to collectionDetaill,
                                "name" to nama,
                                "img" to foto
                            )
                            firestore.collection("users").document(email).collection("plan").document(codeDetail)
                                .set(setPlan)
                        }
                    }
                }
        }
    }
}