package com.example.cameraxapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.cardview.widget.CardView

class RegistrationActivity : AppCompatActivity() {
    private lateinit var txtCompany: TextView
    private lateinit var txtAlreadySignIn: TextView
    private lateinit var btnSignUp:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        txtCompany = findViewById(R.id.textView)
        txtAlreadySignIn = findViewById(R.id.txtAlreadySignIn)
        btnSignUp = findViewById(R.id.btnSignUp)

        val spannable1 = SpannableStringBuilder(getString(R.string.company_name))
        spannable1.setSpan(
            ForegroundColorSpan(getColor(R.color.green)),
            0,
            2,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        txtCompany.text = spannable1

        val spannable2 = SpannableStringBuilder(getString(R.string.already_have_an_account))
        spannable2.setSpan(
            ForegroundColorSpan(getColor(R.color.green)),
            25, 32,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        txtAlreadySignIn.text = spannable2

        btnSignUp.setOnClickListener {
            startActivity(Intent(this@RegistrationActivity,SignUpActivity::class.java))
        }

        txtAlreadySignIn.setOnClickListener {
            startActivity(Intent(this@RegistrationActivity,SignInActivity::class.java))
        }
    }
}