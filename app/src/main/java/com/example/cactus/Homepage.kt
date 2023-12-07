package com.example.cactus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Homepage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        val button: Button = findViewById(R.id.button)

        // ตรวจสอบว่าปุ่มถูกคลิก
        button.setOnClickListener {
            // สร้าง Intent เพื่อเรียกใช้ MainActivity
            val intent = Intent(this, MainActivity::class.java)
            // เรียกใช้ MainActivity
            startActivity(intent)
        }
    }
}
