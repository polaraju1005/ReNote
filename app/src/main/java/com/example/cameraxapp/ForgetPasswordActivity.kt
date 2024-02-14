package com.example.cameraxapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.widget.ImageView
import android.widget.TextView

class ForgetPasswordActivity : AppCompatActivity() {

    private lateinit var btnBack: ImageView
    private lateinit var txtCompanyName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        txtCompanyName = findViewById(R.id.textView)
        btnBack = findViewById(R.id.btnBack)

        val spannable = SpannableStringBuilder(getString(R.string.company_name))
        spannable.setSpan(
            ForegroundColorSpan(getColor(R.color.green)),
            0,
            2,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        txtCompanyName.text = spannable

        btnBack.setOnClickListener {
            startActivity(Intent(this@ForgetPasswordActivity,SignInActivity::class.java))
            finishAffinity()
        }
    }
}