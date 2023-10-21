package com.ivanojok.fire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val auth = FirebaseAuth.getInstance()
        val db = FirebaseDatabase.getInstance()

        val emailId = findViewById<EditText>(R.id.email)
        val passwordId = findViewById<EditText>(R.id.password)
        val nameId = findViewById<EditText>(R.id.name)
        val phoneId = findViewById<EditText>(R.id.phone)


        val button = findViewById<Button>(R.id.sign_in)
        button.setOnClickListener {
            when {
                emailId.text.isEmpty() -> { }
                passwordId.text.isEmpty() -> {}
                nameId.text.isEmpty() -> {}
                phoneId.text.isEmpty() -> {}

                else -> {
                    val email = emailId.text.toString()
                    val password = passwordId.text.toString()
                    val name = nameId.text.toString()
                    val phone = phoneId.text.toString()
                    val credential = auth.createUserWithEmailAndPassword(email, password)
                    credential.addOnSuccessListener {

                        val userId = it.user?.uid ?: "IdNull"
                        val user = UserModel(userId, name, email, password, phone)
                        db.reference.child("users").child(userId).setValue(user).addOnSuccessListener {
                            startActivity(Intent(this, HomeActivity::class.java))
                            finish()
                        }.addOnFailureListener {
                            Toast.makeText(this, "An error occurred; ${it.message}", Toast.LENGTH_LONG).show()

                        }

                    }.addOnFailureListener {
                        Toast.makeText(this, "An error occurred; ${it.message}", Toast.LENGTH_LONG).show()

                    }
                }
            }

        }
    }
}