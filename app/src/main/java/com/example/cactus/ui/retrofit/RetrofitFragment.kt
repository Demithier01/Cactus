package com.example.cactus.ui.retrofit

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
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.cactus.R
import com.example.cactus.api.RetrofitInstance
import com.example.cactus.api.ApiService
import com.example.cactus.api.ServiceFactory
import com.example.cactus.databinding.FragmentRetrofitBinding
import com.example.cactus.model.SpeciesItem
import com.example.cactus.view.SpeciesAdapter
import com.google.firebase.auth.FirebaseAuth
import java.util.Locale

class RetrofitFragment : Fragment() {
    private lateinit var binding: FragmentRetrofitBinding
    private lateinit var viewModel: RetrofitViewModel
    private lateinit var adapterSpecies : SpeciesAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRetrofitBinding.inflate(inflater, container, false)

        val retrofit = RetrofitInstance.getRetrofitInstance()
        val apiService = retrofit.create(ApiService::class.java)

        val viewModelFactory = ServiceFactory(apiService)
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

        binding.btnLogout.setOnClickListener {
            SweetAlertDialog(requireContext(), SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Log Out")
                .setContentText("Do you want to logout?")
                .setConfirmText("Ok")
                .setCancelText("Cancel")
                .setConfirmClickListener { dialog ->
                    FirebaseAuth.getInstance().signOut()
                    findNavController().navigate(R.id.action_retrofitFragment_to_loginFragment)
                    dialog.dismissWithAnimation()
                }
                .setCancelClickListener { it.dismissWithAnimation() }
                .show()
        }
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
                showSweetAlertDialog("")
            } else {
                adapterSpecies.setCheckedList(checkedList)
            }
        })
    }
    private fun showSweetAlertDialog(message: String) {
        SweetAlertDialog(requireContext(), SweetAlertDialog.ERROR_TYPE)
            .setTitleText("No Data found!!")
            .setConfirmText("Close")
            .show()

    }

}
