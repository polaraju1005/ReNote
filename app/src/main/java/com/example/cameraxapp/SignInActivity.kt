package com.example.cameraxapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView

class SignInActivity : AppCompatActivity() {

    private lateinit var txtCompanyName: TextView
    private lateinit var btnBack: ImageView
    private lateinit var txtForgot:TextView
    private lateinit var btnLogin:CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        txtCompanyName = findViewById(R.id.textView)
        btnBack = findViewById(R.id.btnBack)
        txtForgot = findViewById(R.id.txtForgotPass)
        btnLogin = findViewById(R.id.btnLogin)

        val spannable = SpannableStringBuilder(getString(R.string.company_name))
        spannable.setSpan(
            ForegroundColorSpan(getColor(R.color.green)),
            0,
            2,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        txtCompanyName.text = spannable

        btnBack.setOnClickListener {
            startActivity(Intent(this@SignInActivity,RegistrationActivity::class.java))
            finishAffinity()
        }

        txtForgot.setOnClickListener {
            startActivity(Intent(this@SignInActivity,ForgetPasswordActivity::class.java))
        }

        btnLogin.setOnClickListener {
            startActivity(Intent(this@SignInActivity,MainActivity::class.java))
        }

    }
}