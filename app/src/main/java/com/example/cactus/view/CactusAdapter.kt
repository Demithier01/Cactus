package com.example.cactus.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cactus.databinding.HomepageItemBinding
import com.example.cactus.model.Cactus
class CactusAdapter(private var items: List<Cactus>) :
    RecyclerView.Adapter<CactusAdapter.CactusViewHolder>() {

    lateinit var onItemClick: (Cactus) -> Unit
    class CactusViewHolder(private val binding: HomepageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var textView: TextView = binding.textView
        var imageView: ImageView = binding.imageView

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CactusViewHolder {
        return  CactusViewHolder(HomepageItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }
    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CactusViewHolder, position: Int) {
        val cactus = items[position]

        // ใช้ Glide เพื่อโหลดรูปภาพและแสดงบน ImageView
        Glide.with(holder.itemView)
            .load(cactus.image)
            .into(holder.imageView)

        // กำหนดชื่อ Cactus ใน TextView
        holder.textView.text = cactus.name

        // กำหนดการทำงานเมื่อคลิกที่รายการ
        holder.itemView.setOnClickListener {
            onItemClick.invoke(cactus)
        }
    }

    // อัปเดตรายการเมื่อกรองตรง search
    fun setFilteredList(cactusList: List<Cactus>) {
        this.items = cactusList
        notifyDataSetChanged()
    }


}

