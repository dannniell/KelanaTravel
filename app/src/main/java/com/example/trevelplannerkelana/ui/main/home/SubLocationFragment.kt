package com.example.trevelplannerkelana.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trevelplannerkelana.R
import com.example.trevelplannerkelana.adapter.SubLocationAdapter
import com.example.trevelplannerkelana.data.SubLocation
import com.example.trevelplannerkelana.databinding.FragmentSubLocationBinding
import com.google.firebase.firestore.FirebaseFirestore


class SubLocationFragment : Fragment() {
    private var _binding: FragmentSubLocationBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_subLocationFragment_to_homeFragment)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSubLocationBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    lateinit var collectionData: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firestore = FirebaseFirestore.getInstance()
        collectionData = arguments?.getString("sublocation").toString()

        var dataSub: ArrayList<SubLocation>?

        firestore.collection(collectionData).get().addOnSuccessListener {
            result ->
            dataSub = ArrayList()
            for (document in result){
                dataSub!!.add(
                    SubLocation(
                        document.data["name"].toString(),
                        document.data["img"].toString(),
                        document.data["code"].toString(),
                        document.data["map"].toString()
                    )
                )
            }
            binding.recyclerSubLocation.layoutManager = LinearLayoutManager(activity)
            binding.recyclerSubLocation.adapter = SubLocationAdapter(dataSub!!, collectionData)
        }
    }


}