package com.example.cactus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    var cv1: CardView? = null
    var cv2: CardView? = null
    var cv3: CardView? = null
    var cv4: CardView? = null
    var cv5: CardView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cv1 = findViewById(R.id.Cardview1)
        cv2 = findViewById(R.id.Cardview2)
        cv3 = findViewById(R.id.Cardview3)
        cv4 = findViewById(R.id.Cardview4)
        cv5 = findViewById(R.id.Cardview5)

        //คำสั่งเปลี่ยนหน้า
        cv1!!.setOnClickListener{
            val intent = Intent(this,Ech_page::class.java)
            startActivity(intent)
        }
    }
}