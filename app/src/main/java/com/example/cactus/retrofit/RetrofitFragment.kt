package com.example.cactus.retrofit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cactus.R
import com.example.cactus.api.RetrofitInstance
import com.example.cactus.api.SpeciesService
import com.example.cactus.api.SpeciesServiceFactory
import com.example.cactus.databinding.FragmentRetrofitBinding
import com.example.cactus.model.SpeciesItem
import com.example.cactus.view.SpeciesAdapter
import java.util.Locale

class RetrofitFragment : Fragment() {
    private lateinit var binding: FragmentRetrofitBinding
    private lateinit var viewModel: RetrofitViewModel
    private lateinit var adapterSpecies : SpeciesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRetrofitBinding.inflate(inflater, container, false)
        // Create the Retrofit instance and the SpeciesService
        val retrofit = RetrofitInstance.getRetrofitInstance()
        val speciesService = retrofit.create(SpeciesService::class.java)
        // Create the ViewModel with the SpeciesServiceFactory
        val viewModelFactory = SpeciesServiceFactory(speciesService)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(RetrofitViewModel::class.java)
        viewModel.speciesList.observe(viewLifecycleOwner, Observer { speciesList ->
            adapterSpecies = SpeciesAdapter(speciesList,
                {type ->
                val bundle = Bundle()
                bundle.putParcelable("id",type)
                findNavController().navigate(R.id.action_retrofitFragment_to_detailFragment,bundle)
            },
                { type ->
                    val bundle = Bundle()
                    bundle.putParcelable("id", type)
                    findNavController().navigate(
                        R.id.action_retrofitFragment_to_updateFragment,bundle)
                },
                { type ->
                    viewModel.deleteItem(type) {sucess ->
                        if (sucess){
                            Toast.makeText(requireContext(), "Deleted successfully", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(requireContext(), "Failed to delete", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                viewModel,
                requireContext()
            )

            binding.rvRetrofit.adapter = adapterSpecies
            updateUI(speciesList)
        })

        binding.buttonAdd.setOnClickListener {
            findNavController().navigate(R.id.action_retrofitFragment_to_addDataFragment)
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchData()


        binding.search.setOnQueryTextListener(object :androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                checkList(newText)
                return true
            }
        })
    }
    private fun updateUI(speciesList: List<SpeciesItem>) {
        binding.rvRetrofit.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
            adapter = adapterSpecies
        }
    }
    private fun checkList(query: String?) {
        viewModel.speciesList.observe(viewLifecycleOwner, Observer { type ->
            val checkedList = type.filter { it.name!!.lowercase(Locale.ROOT).contains(query?.lowercase(Locale.ROOT) ?: "") }
            if (checkedList.isEmpty()) {
                Toast.makeText(requireContext(), "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                adapterSpecies.setCheckedList(checkedList)
            }
        })
    }
}

//        viewModel.updateData(SpeciesItem(null,null,null))
//        viewModel.createData(SpeciesItem(
//            "",
//            "",
//            "" ))
//        viewModel.deleteData("")
