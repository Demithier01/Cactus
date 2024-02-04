package com.example.cactus.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cactus.R
import com.example.cactus.RetrofitViewModel
import com.example.cactus.databinding.RetrofitItemBinding
import com.example.cactus.model.SpeciesItem

class SpeciesAdapter(private var speciesList: List<SpeciesItem>,
                     private val onClickItem:(SpeciesItem) -> Unit,
                     private val onEditItem: (SpeciesItem) -> Unit,
                     private val onDeleteItem: (SpeciesItem) -> Unit,
):
    RecyclerView.Adapter<SpeciesAdapter.SpeciesViewHolder>() {
    private lateinit var binding: RetrofitItemBinding
   inner class SpeciesViewHolder(private val binding: RetrofitItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentItem:SpeciesItem){
            binding.textName.text = currentItem.name
            binding.textID.text = currentItem.id
//            Log.d("ABC","${position}-  ${currentItem.imageUrl}")
            Glide.with(itemView.context)
                .load(currentItem.image)
                .placeholder(R.drawable.image)
                .error(R.drawable.ic_img_error)
                .into(binding.img)
            itemView.setOnClickListener{
                onClickItem.invoke(currentItem)
            }
            binding.btnEdit.setOnClickListener {
                onEditItem.invoke(currentItem)
            }
            binding.btnDelete.setOnClickListener {
                onDeleteItem.invoke(currentItem)
            }
        }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesViewHolder {
        binding = RetrofitItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpeciesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SpeciesViewHolder, position: Int) {
        val currentItem = speciesList[position]
        holder.bind(currentItem)

    }
    override fun getItemCount() = speciesList.size
    //update list search
    fun setCheckedList(checkedList: List<SpeciesItem>) {
        this.speciesList = checkedList
        notifyDataSetChanged()
    }
}

