package com.example.appchat.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.appchat.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.bntLogin

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        bntLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
                Toast.makeText(applicationContext,"email and password are required", Toast.LENGTH_LONG).show()
            }else{
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this){
                        if (it.isSuccessful){
                            etEmail.setText("")
                            etPassword.setText("")
                            val inten = Intent(this@LoginActivity, UsersActivity::class.java)
                            startActivity(inten)
                        }else{
                            Toast.makeText(applicationContext,"email or password invalid", Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }
        bntSignUp.setOnClickListener {
            val inten = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(inten)
        }
    }
}