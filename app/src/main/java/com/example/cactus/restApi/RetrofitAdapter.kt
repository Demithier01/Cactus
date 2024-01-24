package com.example.cactus.restApi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cactus.databinding.RetrofitItemBinding
import com.example.cactus.restApi.model.SpeciesItem

class RetrofitAdapter(private val speciesList: List<SpeciesItem>) :
    RecyclerView.Adapter<RetrofitAdapter.SpeciesViewHolder>() {
    private var binding: RetrofitItemBinding? = null

    class SpeciesViewHolder(private val binding: RetrofitItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var tvName = binding.textName
        var tvTitle = binding.textTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesViewHolder {
        binding = RetrofitItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpeciesViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: SpeciesViewHolder, position: Int) {
        val currentItem = speciesList[position]
        holder.apply {
            binding?.apply {
                tvName.text = currentItem.name
                tvTitle.text = currentItem.title
            }
        }
    }

    override fun getItemCount() = speciesList.size
}