package com.example.trevelplannerkelana.ui.main.home

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trevelplannerkelana.databinding.FragmentHomeBinding
import com.example.trevelplannerkelana.adapter.LocationAdapter
import com.example.trevelplannerkelana.data.Location
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //inisialisasi recycle view
        //connect recycle view ke item viewnya

        val firestore = FirebaseFirestore.getInstance()

        var dataLoc: ArrayList<Location>?

        firestore.collection("location").get().addOnSuccessListener {
                result ->
            dataLoc = ArrayList()
            for (document in result){
                dataLoc!!.add(
                    Location(
                        document.data["name"].toString(),
                        document.data["img"].toString()
                    )
                )
            }
            binding.recyclerLocation.layoutManager = LinearLayoutManager(activity)
            binding.recyclerLocation.adapter = LocationAdapter(dataLoc!!)
            //val adapter = LocationAdapter(listener, data!!)
            //binding.recyclerLocation.adapter = adapter
        }
    }

    /*
    private val listener = LocationAdapter.OnClickListener { location ->
        // Add action to navigate
//        findNavController().navigate(R.id.action_homeFragment_to_subLocationFragment)
        Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_subLocationFragment);
    }

     */

    /*
    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)//akses home
        intent.addCategory(Intent.CATEGORY_HOME)//menambah kategori home agar ketika di back langsung menuju home
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP//menghapus history activity yang dibuka
        startActivity(intent)
    }
     */
}