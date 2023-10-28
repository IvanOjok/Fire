package com.ivanojok.fire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.FirebaseDatabase
import java.util.UUID

class AdminActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        val db = FirebaseDatabase.getInstance()


        val button = findViewById<Button>(R.id.add)
        button.setOnClickListener {

            val name = findViewById<EditText>(R.id.name).text.toString()
            val brand = findViewById<EditText>(R.id.brand).text.toString()
            val price = findViewById<EditText>(R.id.price).text.toString()
            val quantity = findViewById<EditText>(R.id.quantity).text.toString()
            val size = findViewById<EditText>(R.id.size).text.toString()
            val description = findViewById<EditText>(R.id.desc).text.toString()


            val product = ProductModel(name, brand, price, size, description, quantity)
            val uuid = UUID.randomUUID()
            db.reference.child("products").child(uuid.toString()).setValue(product)

        }


    }
}