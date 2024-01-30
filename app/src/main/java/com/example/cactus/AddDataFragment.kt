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
import com.example.cactus.databinding.FragmentAddDataBinding
import com.example.cactus.model.SpeciesItem

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
//        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(RetrofitViewModel::class.java)
        val sharedViewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(RetrofitViewModel::class.java)

        binding.btnAdd.setOnClickListener {
            val name = binding.addName.text.toString()
            val title = binding.addTitle.text.toString()
            val imageUrl = binding.addUrlImg.text.toString()

            if (name.isNotEmpty() && title.isNotEmpty() && imageUrl.isNotEmpty()) {
                val newSpeciesItem = SpeciesItem(name, title, imageUrl)
                sharedViewModel.createData(newSpeciesItem)

                // Navigate back to RetrofitFragment
                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
}