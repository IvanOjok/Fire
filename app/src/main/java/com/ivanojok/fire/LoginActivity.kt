package com.ivanojok.fire

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.values
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val auth = FirebaseAuth.getInstance()
        val db = FirebaseDatabase.getInstance()

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
                        val uid = it.user?.uid ?:"IdNull"

                        val y = db.reference.child("users").child(uid).addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                val key = snapshot.key
                                Log.d("Snap", "${snapshot.value}")
                                val data = snapshot.getValue(UserModel::class.java)
                                Log.d("Key", "$key")
                                Log.d("Data P", "${data?.position}")
                                Log.d("Data N", "${data?.name}")


                                if (data?.position == "Owner") {
                                    startActivity(Intent(this@LoginActivity, AdminActivity::class.java))
                                    finish()
                                } else {
                                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                                    finish()
                                }

                            }

                            override fun onCancelled(error: DatabaseError) {
                                Log.d("Error", "${error.message}")
                            }

                        })
                        //x.first()



                    }.addOnFailureListener {
                        Toast.makeText(this, "An error occurred: ${it.message}", Toast.LENGTH_LONG).show()
                    }

                }
            }
        }


    }
}