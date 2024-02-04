package com.example.cactus

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
import com.example.cactus.databinding.FragmentUpdateBinding
import com.example.cactus.model.SpeciesItem

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
//
//        speciesItem = arguments?.getParcelable("id") ?: SpeciesItem()
//
//        // Populate UI with current data
//        binding.editName.setText(speciesItem.name)
//        binding.editTitle.setText(speciesItem.title)
//        binding.editImage.setText(speciesItem.image)
//        binding.btnUp.setOnClickListener {
//            // Update the speciesItem with new data
//            val name = binding.editName.text.toString()
//            val title = binding.editTitle.text.toString()
//            val image = binding.editImage.text.toString()
//            // Update other fields as needed
//            if (name.isNotEmpty() && title.isNotEmpty() && image.isNotEmpty()) {
//                val updatedSpeciesItem = SpeciesItem(speciesItem.id, name, title, image)
//                viewModel.updateData(updatedSpeciesItem)
//                // Navigate back to RetrofitFragment
//                findNavController().popBackStack()
//            } else {
//                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
//            }
//        }
    return binding.root
    }
}