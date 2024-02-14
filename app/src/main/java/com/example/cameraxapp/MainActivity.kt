package com.example.cameraxapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    private lateinit var btnLogout: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogout = findViewById(R.id.btnLogout)
        btnLogout.setOnClickListener {
            startActivity(Intent(this@MainActivity,RegistrationActivity::class.java))
        }
    }
}