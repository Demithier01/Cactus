package com.example.cactus.information

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cactus.R
import com.example.cactus.databinding.FragmentInformentsBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class InformentsFragment : Fragment() {
    private lateinit var binding : FragmentInformentsBinding
    private lateinit var informentList: ArrayList<Informents>
    private lateinit var firebaseRef : DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentInformentsBinding.inflate(inflater,container,false)
        binding.btnAdd.setOnClickListener{
            findNavController().navigate(R.id.action_informentsFragment_to_addInformentsFragment)
        }
        firebaseRef = FirebaseDatabase.getInstance().getReference("information")
        informentList = arrayListOf()

        fetchData()

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
        }
        return binding.root
    }


    private fun fetchData() {
        firebaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                informentList.clear()
                if (snapshot.exists()){
                    for (informentSnap in snapshot.children){
                        val information = informentSnap.getValue(Informents::class.java)
                        informentList.add(information!!)
                    }
                }
                val rvAdapter = InformentsAdapter(informentList)
                binding.recyclerView.adapter = rvAdapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context,"error : $error", Toast.LENGTH_SHORT).show()
            }

        })
    }
}

