package com.ivanojok.fire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val db = FirebaseDatabase.getInstance()
        db.reference.child("products").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<ProductModel>()
                for (i in snapshot.children) {
                    Log.d("Key", "${i.key}")
                    val x = i.getValue(ProductModel::class.java)
                    if (x != null) {
                        list.add(x)
                    }

                }

                //call recycler view

                for (i in list) {
                    Log.d("list", "$i")
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}