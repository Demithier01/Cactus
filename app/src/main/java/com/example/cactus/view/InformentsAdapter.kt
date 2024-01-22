package com.example.cactus.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.cactus.InformentsFragmentDirections
import com.example.cactus.R
import com.example.cactus.databinding.InformationItemBinding
import com.example.cactus.model.Informents
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso

class InformentsAdapter (private val informentList:ArrayList<Informents> ): RecyclerView.Adapter<InformentsAdapter.ViewHolder>() {
    class ViewHolder(val binding: InformationItemBinding): RecyclerView.ViewHolder(binding.root) {

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InformentsAdapter.ViewHolder {
        return  ViewHolder(InformationItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = informentList[position]
        holder.apply {
            binding.apply {
                tvName.text = currentItem.name
                tvDescription.text = currentItem.description
                Picasso.get()
                    .load(currentItem.imgUrl)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_error)
                    .into(imageView)

                //update data

                //delete data
                rvContainer.setOnLongClickListener {
                    MaterialAlertDialogBuilder(holder.itemView.context)
                        .setTitle("Delete item ")
                        .setMessage("Are You Sure??")
                        .setPositiveButton("Yes") { _, _ ->
                            val firebaseRef =
                                FirebaseDatabase.getInstance().getReference("information")
                            val storageRef = FirebaseStorage.getInstance().getReference("images")
                            //storage
                            storageRef.child(currentItem.id.toString()).delete()

                            //realtime database
                            firebaseRef.child(currentItem.id.toString()).removeValue()
                                .addOnSuccessListener {
                                    Toast.makeText(
                                        holder.itemView.context,
                                        "Item removed",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                .addOnFailureListener { error ->
                                    Toast.makeText(
                                        holder.itemView.context, "error ${error.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                        }
                        .setNegativeButton("No") { _, _ ->
                            Toast.makeText(holder.itemView.context, "canceled", Toast.LENGTH_SHORT)
                                .show()
                        }
                        .show()
                    return@setOnLongClickListener true
                }
            }
        }
    }
        override fun getItemCount() = informentList.size
    }


