package com.example.cactus.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cactus.R
import com.example.cactus.model.Cactus

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
