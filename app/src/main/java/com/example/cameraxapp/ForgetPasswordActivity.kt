package com.example.cameraxapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.util.Patterns
import android.view.MotionEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ForgetPasswordActivity : AppCompatActivity() {

    private lateinit var etEmail: TextInputEditText
    private lateinit var etEmailL:TextInputLayout

    private lateinit var btnBack: ImageView
    private lateinit var txtCompanyName: TextView

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        txtCompanyName = findViewById(R.id.textView)
        btnBack = findViewById(R.id.btnBack)
        etEmail = findViewById(R.id.etEmail)
        etEmailL = findViewById(R.id.textInputLayoutEmail)

        etEmail.requestFocus()
        showKeyboard()

        etEmail.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // Start the next activity
                if (validateEmail()) {
                    startActivity(Intent(this@ForgetPasswordActivity, OTPActivity::class.java))
                }
                true
            } else {
                false
            }
        })



        etEmail.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP){
                val drawableRight = 2
                if (event.rawX >= (etEmail.right - etEmail.compoundDrawables[drawableRight].bounds.width())) {
                    // Click listener for drawableRight

                    drawableClicked()
                    return@setOnTouchListener true
                }
            }
            false

        }

        etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (validateEmail()){
                    etEmailL.error = null
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (validateEmail()){
                    etEmailL.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if (validateEmail()){
                    etEmailL.error = null
                }
            }

        })

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

    private fun drawableClicked() {
        if (validateEmail()){
            startActivity(Intent(this@ForgetPasswordActivity,OTPActivity::class.java))
        }
    }

    private fun showKeyboard() {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(etEmail, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun validateEmail():Boolean {
        val email = etEmail.text.toString().trim()

        if (email.isEmpty()) {
            etEmailL.error = "Email cannot be empty"
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmailL.error = "please enter a valid email"
            return false
        } else {
            etEmailL.error = null // Clear the error
        }
        return true
    }
}