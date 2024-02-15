package com.example.cameraxapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.util.Patterns
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

class SignInActivity : AppCompatActivity() {

    private lateinit var txtCompanyName: TextView
    private lateinit var btnBack: ImageView
    private lateinit var txtForgot:TextView
    private lateinit var btnLogin:CardView

    //edittext Fields
    private lateinit var etEmailL:TextInputLayout
    private lateinit var etEmail:TextInputEditText

    private lateinit var etPasswordL:TextInputLayout
    private lateinit var etPassword:TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        txtCompanyName = findViewById(R.id.textView)
        btnBack = findViewById(R.id.btnBack)

        etEmailL = findViewById(R.id.textInputLayoutEmail)
        etEmail = findViewById(R.id.etEmail)

        etPasswordL = findViewById(R.id.textInputLayoutPassword)
        etPassword = findViewById(R.id.etPassword)

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

        //requesting focus to the edittext field when the screen is opened
        etEmail.requestFocus()
        showKeyboard()

        etEmail.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validateEmail()
            }
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

        btnBack.setOnClickListener {
            startActivity(Intent(this@SignInActivity,RegistrationActivity::class.java))
            finishAffinity()
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

        etPassword.setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // Start the next activity
               if (validatePassword() and validateEmail()){
                   startActivity(Intent(this@SignInActivity,MainActivity::class.java))
               }
                true
            } else {
                false
            }
        })


        txtForgot.setOnClickListener {
            startActivity(Intent(this@SignInActivity,ForgetPasswordActivity::class.java))
        }

        btnLogin.setOnClickListener {
            if (validatePassword() && validateEmail())
                startActivity(Intent(this@SignInActivity,MainActivity::class.java))
        }

    }

    private fun validatePassword():Boolean {
        val password = etPassword.text.toString().trim()

        //val passwordRegex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#\$%^&*()])(?=\\S+\$).*$"
        //val pattern = Pattern.compile(passwordRegex)

        if (password.isEmpty()){
            etPasswordL.error = "Password Cannot be empty"
            return false
        }else if (password.length<8){
            etPasswordL.error = "Enter a Valid Password"
            return false
        }else{
          etPasswordL.error = null
        }
        return true
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