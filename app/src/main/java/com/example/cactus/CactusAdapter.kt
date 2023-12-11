package com.example.cactus

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler

class CactusAdapter(private  val cactusList:List<Cactus>)
    : RecyclerView.Adapter<CactusAdapter.CactueViewHolder>()
{
    var onItemClick : ((Cactus) -> Unit)? = null
    class CactueViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var textView:TextView=itemView.findViewById(R.id.textView)
        var imageView:ImageView=itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CactueViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.type_item,parent,false)
        return  CactueViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cactusList.size
    }

    override fun onBindViewHolder(holder: CactueViewHolder, position: Int) {
        val cactus = cactusList[position]
        Log.d("AA", "bind item $position")
        holder.imageView.setImageResource(cactus.image)
        holder.textView.text=cactus.name

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(cactus)
        }
    }
}
//ViewHolder ช่วยให้RecyclerView ทำงานได้เร็วขึ้น โดยจะเก็บviewของitemไว้ชั่วคราว
// และเมื่อ RecyclerView ต้องการแสดงรายการ มันจะดึง ViewHolder ที่มีอยู่มาใช้แทนที่จะสร้าง View ใหม่ทุกครั้

//RecyclerView เป็นcomponent ที่ใช้แสดงรายการข้อมูลเรียงเป็นแถว
//การทำงาน
//LayoutManager เป็นส่วนที่ทำหน้าที่จัดเรียงตำแหน่งของ View ต่างๆ ภายใน RecyclerView
//Adapter เป็นส่วนที่ทำหน้าที่สร้าง View ต่างๆ ภายใน RecyclerView
//ViewHolder เป็นส่วนที่ทำหน้าที่เก็บข้อมูลของ View ต่างๆ ภายใน RecyclerView