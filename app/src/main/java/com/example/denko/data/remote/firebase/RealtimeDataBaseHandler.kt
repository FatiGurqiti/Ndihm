package com.example.denko.data.remote.firebase

import com.example.denko.domain.model.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson

class RealtimeDataBaseHandler {
    private val gson = Gson() //TODO("Inject this")

    companion object {
        private const val PATH = "requestsTest"
    }

    fun addNewValue(user: User) {
        val database = FirebaseDatabase.getInstance().reference
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val userJson = gson.toJson(user)
                user.id?.let {
                    database.child(PATH).child(it).setValue(userJson)
                    database.removeEventListener(this)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle errors here.
                println("hajde: error: $databaseError")
            }
        })
    }
}