package com.example.cactus.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cactus.R
import com.example.cactus.databinding.RetrofitItemBinding
import com.example.cactus.model.SpeciesItem

class SpeciesAdapter(private var speciesList: List<SpeciesItem>) :
    RecyclerView.Adapter<SpeciesAdapter.SpeciesViewHolder>() {
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
                Glide.with(img.context)
                    .load(currentItem.imageUrl)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_error)
                    .into(img)

            }
        }
    }
    override fun getItemCount() = speciesList.size

    //update list search
    fun setCheckedList(checkedList: List<SpeciesItem>) {
        this.speciesList = checkedList
        notifyDataSetChanged()
    }


}