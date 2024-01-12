package com.example.cactus

import CactusAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cactus.databinding.FragmentHomePageBinding
import com.example.cactus.viewmodel.HomepageViewModel
import java.util.Locale

class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding
    private lateinit var cactusAdapter: CactusAdapter
    private val navController by lazy { findNavController() }
    private lateinit var viewModel: HomepageViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[HomepageViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)

        cactusAdapter = CactusAdapter(viewModel.cactusList)

        cactusAdapter.onItemClick = { cactus ->
            val bundle = Bundle()
            bundle.putParcelable("cactus", cactus)
            navController.navigate(R.id.action_homePageFragment_to_detailFragment, bundle)
        }

        binding.recyclerView.adapter = cactusAdapter

        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
    }

    private fun filterList(query: String?) {
        val filteredList = viewModel.cactusList.filter { it.name.lowercase(Locale.ROOT).contains(query?.lowercase(Locale.ROOT) ?: "") }
        if (filteredList.isEmpty()) {
            Toast.makeText(requireContext(), "No Data found", Toast.LENGTH_SHORT).show()
        } else {
            cactusAdapter.setFilteredList(filteredList)
        }
    }
}
