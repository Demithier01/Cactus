package com.example.cactus.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bumptech.glide.Glide
import com.example.cactus.R
import com.example.cactus.databinding.ItemRetrofitBinding
import com.example.cactus.ui.retrofit.RetrofitViewModel
import com.example.cactus.model.SpeciesItem

class SpeciesAdapter(
    private var speciesList: List<SpeciesItem>,
    private val onClickItem: (SpeciesItem) -> Unit,
    private val onEditItem: (SpeciesItem) -> Unit,
    private val onDeleteItem: (SpeciesItem) -> Unit,
    viewModel: RetrofitViewModel,
    private val requireContext: Context
):
    RecyclerView.Adapter<SpeciesAdapter.SpeciesViewHolder>() {
    private lateinit var binding: ItemRetrofitBinding

   inner class SpeciesViewHolder(private val binding: ItemRetrofitBinding) :
        RecyclerView.ViewHolder(binding.root) {
       fun bind(currentItem: SpeciesItem) {
           binding.textName.text = currentItem.name
           binding.textID.text = currentItem.id
//            Log.d("ABC","${position}-  ${currentItem.imageUrl}")
           Glide.with(itemView.context)
               .load(currentItem.image)
               .placeholder(R.drawable.image)
               .error(R.drawable.ic_img_error)
               .into(binding.img)
           itemView.setOnClickListener {
               onClickItem.invoke(currentItem)
           }
           binding.btnEdit.setOnClickListener {
               onEditItem.invoke(currentItem)
           }
           binding.btnDelete.setOnClickListener {
               showDeleteConfirmationDialog(currentItem)
           }
       }

       private fun showDeleteConfirmationDialog(speciesItem: SpeciesItem) {
           SweetAlertDialog(requireContext, SweetAlertDialog.WARNING_TYPE)
               .setTitleText("Delete")
               .setContentText("Are you sure want to delete this ${speciesItem.name}?")
               .setConfirmText("Delete")
               .setCancelText("Cancel")
               .setConfirmClickListener {
                   onDeleteItem.invoke(speciesItem)
                   it.dismissWithAnimation()
               }
               .setCancelClickListener { it.dismissWithAnimation()
               }.show()
       }
   }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpeciesViewHolder {
        binding = ItemRetrofitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpeciesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SpeciesViewHolder, position: Int) {
        val currentItem = speciesList[position]
        holder.bind(currentItem)
    }
    override fun getItemCount() = speciesList.size

    fun setCheckedList(checkedList: List<SpeciesItem>) {
        this.speciesList = checkedList
        notifyDataSetChanged()
    }
}

