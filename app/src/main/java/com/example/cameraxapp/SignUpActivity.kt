package com.example.cameraxapp

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
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.android.material.textfield.TextInputEditText
import android.widget.TextView.OnEditorActionListener
import com.google.android.material.textfield.TextInputLayout

class SignUpActivity : AppCompatActivity() {
    private lateinit var txtCompanyName: TextView
    private lateinit var btnBack: ImageView
    private lateinit var btnSignUp: CardView

    private lateinit var etEmail: TextInputEditText
    private lateinit var etEmailL:TextInputLayout

    private lateinit var etConfirmL:TextInputLayout
    private lateinit var etConfirm: TextInputEditText

    private lateinit var etPasswordL:TextInputLayout
    private lateinit var etPassword:TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        txtCompanyName = findViewById(R.id.textView)
        btnBack = findViewById(R.id.btnBack)
        btnSignUp = findViewById(R.id.btnSignUp)

        etEmail = findViewById(R.id.etEmail)
        etEmailL = findViewById(R.id.textInputLayoutEmail)

        etPasswordL = findViewById(R.id.textInputLayoutPassword)
        etPassword = findViewById(R.id.etPassword)

        etConfirmL = findViewById(R.id.textInputLayoutCnfPassword)
        etConfirm = findViewById(R.id.etCnfPassword)

        val spannable = SpannableStringBuilder(getString(R.string.company_name))
        spannable.setSpan(
            ForegroundColorSpan(getColor(R.color.green)),
            0,
            2,
            Spannable.SPAN_INCLUSIVE_INCLUSIVE
        )
        txtCompanyName.text = spannable

        //requesting focus to the edittext field when the screen is opened
        etEmail.requestFocus()
        showKeyboard()

        etEmail.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validateEmail()
            }
        }

        etEmail.addTextChangedListener(object :TextWatcher{
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

        etPassword.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus){
                validatePassword()
            }
        }

        etPassword.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (validatePassword()){
                    etPasswordL.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        etConfirm.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (checkConfirmPass()){
                    etConfirmL.error = null
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })


        btnBack.setOnClickListener {
            startActivity(Intent(this@SignUpActivity, RegistrationActivity::class.java))
            finishAffinity()
        }

        btnSignUp.setOnClickListener {
            if (validateEmail() and validatePassword() and checkConfirmPass()){
                startActivity(Intent(this@SignUpActivity, SignUpOTPActivity::class.java))
            }
        }

        etConfirm.setOnEditorActionListener(OnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // Start the next activity
                if (checkConfirmPass() and validateEmail() and validatePassword()){
                    startActivity(Intent(this@SignUpActivity, SignUpOTPActivity::class.java))
                }
                true
            } else {
                false
            }
        })
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

    private fun validatePassword():Boolean{
        val password = etPassword.text.toString().trim()

        if (password.isEmpty()){
            etPasswordL.error = "Password Cannot be empty"
            return false
        }else if (password.length<8){
            etPasswordL.error = "Enter a Valid Password"
            return false
        }else if (!password.contains(Regex("[A-Z]"))){
            etPasswordL.error = " Password should have one capital letter"
            return false
        }else if (!password.contains(Regex("[!@#\$%^&*()]"))){
            etPasswordL.error = "Password should have one special character"
            return false
        }else if (!password.contains(Regex("[0-9]"))){
            etPasswordL.error = " password should have one number"
            return false
        }
        else{
            etPasswordL.error = null
        }
        return true
    }

    private fun checkConfirmPass():Boolean{
        val password = etPassword.text.toString().trim()
        val confirmPassword = etConfirm.text.toString().trim()

        if (confirmPassword!=password){
            etConfirmL.error = "Password's didn't match"
            return false
        }
        return true
    }
}