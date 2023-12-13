package com.example.cactus

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ui.text.input.KeyboardType.Companion.Uri
import androidx.core.net.toUri
import com.example.cactus.model.Cactus

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val imageView:ImageView=findViewById(R.id.detailImageView)
        val textView: TextView=findViewById(R.id.detailTextView)
        val textView2: TextView=findViewById(R.id.detailTextView2)
        val backIcon: ImageView=findViewById(R.id.icBack)
        val textlink: TextView = findViewById(R.id.clickLink)

        val cactus = intent.getParcelableExtra<Cactus>("cactus")
        cactus?.let  { cactus->

            imageView.setImageResource(cactus.image)
            textView.text=cactus.name
            textView2 .text=cactus.description
            textlink.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW,cactus.link.toUri())
                startActivity(browserIntent)
            }
        }
        //กำหนดฟังก์เมื่อทำการกดปุ่ม
        backIcon.setOnClickListener{
            finish() //จะย้อนกลับไปหน้าก่อนหน้านี้
        }

    }
}