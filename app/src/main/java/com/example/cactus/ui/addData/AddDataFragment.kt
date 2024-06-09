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
import com.example.cactus.api.ApiService
import com.example.cactus.api.ServiceFactory
import com.example.cactus.databinding.FragmentAddBinding
import com.example.cactus.model.SpeciesItem
import com.example.cactus.ui.retrofit.RetrofitViewModel

class AddDataFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var viewModel: RetrofitViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(inflater, container, false)

        val apiService = RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
        val viewModelFactory = ServiceFactory(apiService)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(RetrofitViewModel::class.java)

        binding.btnAdd.setOnClickListener {
            val fields = listOf(
                binding.addId to "Enter ID",
                binding.addName to "Enter Name",
                binding.sci to "Enter Scientific Name",
                binding.addDescription to "Enter Description",
                binding.aliment to "Enter Aliment",
                binding.addUrlImg to "Enter URL image"
            )

            var hasEmptyField = false

            fields.forEach { (editText, errorMessage) ->
                val text = editText.editText?.text.toString()
                if (text.isEmpty()) {
                    editText.error =errorMessage
                    hasEmptyField = true
                } else {
                    editText.error = null
                }
            }

            if (!hasEmptyField) {
                val newSpeciesItem = SpeciesItem(
                    binding.addId.editText?.text.toString(),
                    binding.addName.editText?.text.toString(),
                    binding.sci.editText?.text.toString(),
                    binding.addDescription.editText?.text.toString(),
                    binding.aliment.editText?.text.toString(),
                    binding.addUrlImg.editText?.text.toString()
                )
                viewModel.createItem(newSpeciesItem)
                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), "Please fill in all information.", Toast.LENGTH_SHORT).show()
            }

        }

        binding.btnBack.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
        return binding.root
    }

}