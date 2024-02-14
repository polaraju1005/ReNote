package com.example.cameraxapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.widget.TextView

class SignUpActivity : AppCompatActivity() {
    private lateinit var txtCompanyName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        txtCompanyName = findViewById(R.id.textView)
        val spannable = SpannableStringBuilder(getString(R.string.company_name))
        spannable.setSpan(
            ForegroundColorSpan(getColor(R.color.green)),
            0,
            2,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        txtCompanyName.text = spannable

    }
}