package com.example.cameraxapp

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView

class SignUpActivity : AppCompatActivity() {
    private lateinit var txtCompanyName: TextView
    private lateinit var btnBack:ImageView
    private lateinit var btnSignUp:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        txtCompanyName = findViewById(R.id.textView)
        btnBack = findViewById(R.id.btnBack)
        btnSignUp = findViewById(R.id.btnSignUp)

        val spannable = SpannableStringBuilder(getString(R.string.company_name))
        spannable.setSpan(
            ForegroundColorSpan(getColor(R.color.green)),
            0,
            2,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        txtCompanyName.text = spannable

        btnBack.setOnClickListener {
            startActivity(Intent(this@SignUpActivity,RegistrationActivity::class.java))
            finishAffinity()
        }

        btnSignUp.setOnClickListener {
            startActivity(Intent(this@SignUpActivity,OTPActivity::class.java))
        }
    }
}