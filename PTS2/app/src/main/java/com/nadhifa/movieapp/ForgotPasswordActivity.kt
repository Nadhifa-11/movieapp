package com.nadhifa.movieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.core.Context
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_u_p.*

class ForgotPasswordActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mAuth: FirebaseAuth
    companion object{
        fun getLaunchService (from: Context) = Intent(from, ForgotPasswordActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        supportActionBar?.hide()
        tv_forgot.setOnClickListener(this)
        tv_forgot.setOnClickListener(this)
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onClick(p0: View?) {
        when(p0.id){
            R.id.tv_forgot -> startActivity(SignInActivity.getLaunchService(this))
            R.id.tv_forgotten -> forgotPassword()
        }
    }
    private fun forgotPassword() {
        val email = et_email.text.toString()
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, getString(R.string.eror_text),  Toast.LENGTH_SHORT).show()
        }else{
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener{
                if (it.isSuccessful){
                    Toast.makeText(this, "Check email to reset password", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(SignInActivity.getLaunchService(this)))
                }else{
                    Toast.makeText(this, "Failed to reset password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}