package com.example.cactus

import CactusAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cactus.databinding.FragmentHomePageBinding
import com.example.cactus.model.Cactus
import java.util.Locale

class HomePageFragment : Fragment() {

    private lateinit var binding: FragmentHomePageBinding
    private lateinit var cactusAdapter: CactusAdapter
    private lateinit var cactusList: List<Cactus>
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)

        cactusList = CactusGenerator.getItems()
        cactusAdapter = CactusAdapter(cactusList)

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
        if (!query.isNullOrBlank()) {
            val filterList = ArrayList<Cactus>()
            for (i in cactusList) {
                if (i.name.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT))) {
                    filterList.add(i)
                }
            }
            if (filterList.isEmpty()) {
                Toast.makeText(requireContext(), "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                cactusAdapter.setFilteredList(filterList)
            }
        } else {
            cactusAdapter.setFilteredList(cactusList)
        }
    }
}
