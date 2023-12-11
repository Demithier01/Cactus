package com.example.cactus

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.Recycler

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cactusAdapter:CactusAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        cactusAdapter = CactusAdapter(CactusGenerator.getItems())

        cactusAdapter.onItemClick = { cactus ->
            //ส่งข้อมูล cactus
            val intent = Intent(this,DetailActivity::class.java)
            intent.putExtra("cactus", cactus)
            startActivity(intent)
        }
        recyclerView.adapter = cactusAdapter
    }
}