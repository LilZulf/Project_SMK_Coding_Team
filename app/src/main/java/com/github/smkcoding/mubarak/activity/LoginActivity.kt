package com.github.smkcoding.mubarak.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI


import com.github.smkcoding.mubarak.R
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth
    private val RC_SIGN_IN = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        btnlogingo.setOnClickListener(this)
        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            val email = etEmailLog.text.toString().trim()
            val password = etPasswordLog.text.toString().trim()

            if (email.isEmpty()) {
                etEmailLog.error = "Email Harus Diisi"
                etEmailLog.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmailLog.error = "Email Tidak Valid"
                etEmailLog.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 6) {
                etPasswordLog.error = "Password Harus Lebih Dari 6 Karakter"
                etPasswordLog.requestFocus()
                return@setOnClickListener
            }

            loginUser(email, password)
        }

        btnRegister1.setOnClickListener {
            Intent(this@LoginActivity, RegisterActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Intent(this@LoginActivity, MainActivity::class.java).also {
                            intent->
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null) {
            Intent(this@LoginActivity, MainActivity::class.java).also { intent->
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        // RC_SIGN_IN = kode permintaan yang diberikan ke startActivityForResult
        if (requestCode == RC_SIGN_IN) {
            //Jika Berhasil masuk
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
                intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else { //Jika gagal login

                Toast.makeText(this, "Login Dibatalkan", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(p0: View?) {
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(listOf(AuthUI.IdpConfig.GoogleBuilder().build()))
                .setIsSmartLockEnabled(false)
                .build(),
            RC_SIGN_IN)


    }


}
