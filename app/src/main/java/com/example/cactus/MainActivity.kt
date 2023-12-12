
package com.example.cactus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cactus.view.CactusAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cactusAdapter: CactusAdapter

    private lateinit var nameEdit: EditText
    private lateinit var descriptionEdit: EditText
    private lateinit var imageView: ImageView


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

        // Initialize the views
        nameEdit = findViewById(R.id.nameEdit)
        descriptionEdit = findViewById(R.id.descriptionEdit)
        imageView = findViewById(R.id.imageView)

        // Set click listener for "add image" button
        val addImageButton: Button = findViewById(R.id.addimg)
        addImageButton.setOnClickListener { onAddImageClick() }

        // Set click listener for "Add" button
        val addButton: Button = findViewById(R.id.save)
        addButton.setOnClickListener { onAddButtonClick() }

    }
    private fun onAddImageClick() {

    }

    fun onAddButtonClick() {
        val itemName = nameEdit.text.toString()
        val itemDescription = descriptionEdit.text.toString()

    }
}


