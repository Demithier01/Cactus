package com.example.cactus.restApi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cactus.databinding.FragmentRetrofitBinding
import com.example.cactus.restApi.model.SpeciesItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitFragment : Fragment() {
    private lateinit var binding: FragmentRetrofitBinding
    private lateinit var viewModel: RetrofitViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRetrofitBinding.inflate(inflater, container, false)
        // Create the Retrofit instance and the SpeciesService
        val retrofit = RetrofitInstance.getRetrofitInstance()
        val speciesService = retrofit.create(SpeciesService::class.java)

        // Create the ViewModel with the SpeciesServiceFactory
        val viewModelFactory = SpeciesServiceFactory(speciesService)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RetrofitViewModel::class.java)

        viewModel.speciesList.observe(viewLifecycleOwner, Observer { speciesList ->
            updateUI(speciesList)
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchData()
//        viewModel.updateData()
//        viewModel.createData(SpeciesItem("", ""))
//        viewModel.deleteData("")
//
    }
    private fun updateUI(speciesList: List<SpeciesItem>) {
        binding.rvRetrofit.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
            adapter = RetrofitAdapter(speciesList)
        }
    }


}



