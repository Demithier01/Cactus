package com.example.cactus.ui.updateData

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
import com.example.cactus.databinding.FragmentUpdateBinding
import com.example.cactus.model.SpeciesItem
import com.example.cactus.ui.retrofit.RetrofitViewModel

class UpdateFragment : Fragment() {
    private lateinit var binding: FragmentUpdateBinding
    private lateinit var viewModel: RetrofitViewModel
    private lateinit var speciesItem: SpeciesItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)

        val speciesService = RetrofitInstance.getRetrofitInstance().create(SpeciesService::class.java)
        val viewModelFactory = SpeciesServiceFactory(speciesService)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(RetrofitViewModel::class.java)

        speciesItem = arguments?.getParcelable("id") ?: SpeciesItem()

        // ดึงข้อมูลปัจจุบันมาแสดง
        binding.editName.editText?.setText(speciesItem.name)
        binding.editSciName.editText?.setText(speciesItem.sciName)
        binding.editDescription.editText?.setText(speciesItem.description)
        binding.editAliment.editText?.setText(speciesItem.aliment)
        binding.editImage.editText?.setText(speciesItem.image)
        binding.btnUp.setOnClickListener {
            // Update speciesItem ด้วยข้อมูลใหม่
            val name = binding.editName.editText?.text.toString()
            val sci = binding.editSciName.editText?.text.toString()
            val description = binding.editDescription.editText?.text.toString()
            val aliment = binding.editAliment.editText?.text.toString()
            val image = binding.editImage.editText?.text.toString()
            // Update other fields
            if (name.isNotEmpty() && sci.isNotEmpty() && description.isNotEmpty()  && aliment.isNotEmpty() && image.isNotEmpty()) {
                val updatedSpeciesItem = speciesItem.copy(
                    name = name,
                    sciName = sci,
                    description = description,
                    aliment = aliment,
                    image = image
                )
                Toast.makeText(requireContext(), "Updated successfully", Toast.LENGTH_SHORT).show()
                speciesItem.id?.let { it1 -> viewModel.updateDataById(it1, updatedSpeciesItem) }
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