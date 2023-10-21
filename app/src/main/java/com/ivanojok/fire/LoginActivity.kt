package com.ivanojok.fire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val auth = FirebaseAuth.getInstance()

        val emailId = findViewById<EditText>(R.id.phone)
        val passwordId = findViewById<EditText>(R.id.password)

        val signUp = findViewById<TextView>(R.id.sign_up_text)
        signUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }


        val signIn = findViewById<Button>(R.id.sign_in)
        signIn.setOnClickListener {
            when {
                emailId.text.isEmpty() -> { }
                passwordId.text.isEmpty() -> { }
                else -> {
                    val email = emailId.text.toString()
                    val password = passwordId.text.toString()
                    auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    }.addOnFailureListener {
                        Toast.makeText(this, "An error occurred: ${it.message}", Toast.LENGTH_LONG).show()
                    }

                }
            }
        }


    }
}