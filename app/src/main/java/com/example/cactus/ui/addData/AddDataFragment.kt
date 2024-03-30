package com.example.cactus.ui.addData

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.cactus.api.RetrofitInstance
import com.example.cactus.api.SpeciesService
import com.example.cactus.api.SpeciesServiceFactory
import com.example.cactus.databinding.FragmentAddDataBinding
import com.example.cactus.model.SpeciesItem
import com.example.cactus.ui.retrofit.RetrofitViewModel

class AddDataFragment : Fragment() {
    private lateinit var binding: FragmentAddDataBinding
    private lateinit var viewModel: RetrofitViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddDataBinding.inflate(inflater, container, false)

        val speciesService = RetrofitInstance.getRetrofitInstance().create(SpeciesService::class.java)
        val viewModelFactory = SpeciesServiceFactory(speciesService)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(RetrofitViewModel::class.java)

        binding.btnAdd.setOnClickListener {
            val id = binding.addId.editText?.text.toString()
            val name = binding.addName.editText?.text.toString()
            val sci = binding.sci.editText?.text.toString()
            val description = binding.addDescription.editText?.text.toString()
            val aliment = binding.aliment.editText?.text.toString()
            val imageUrl = binding.addUrlImg.editText?.text.toString()

            if ( id.isNotEmpty() && name.isNotEmpty() && sci.isNotEmpty() && description.isNotEmpty()&& aliment.isNotEmpty() && imageUrl.isNotEmpty()) {
                val newSpeciesItem = SpeciesItem(id,name,sci,description,aliment, imageUrl)
                viewModel.createItem(newSpeciesItem)
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
                }
            }
        binding.btnBack.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
        return binding.root
    }
}