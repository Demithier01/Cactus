package com.example.cactus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Homepage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
        //สร้างตัวแปรชื่อ button และนำ View ที่เป็นR.id.butnon มาเก็บไว้ในตัวแปรนี้
        val button: Button = findViewById(R.id.button)

        // ตรวจสอบbutton
        button.setOnClickListener {
            // สร้าง Intent เรียกใช้ MainActivity
            val intent = Intent(this, MainActivity::class.java)
            // เรียกใช้ MainActivity
            startActivity(intent)
        }
    }
}
