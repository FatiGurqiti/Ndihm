package com.example.denko.data.remote.firebase

import com.example.denko.domain.model.Location
import com.example.denko.domain.model.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson

class RealtimeDataBaseHandler {
    private val database = FirebaseDatabase.getInstance().reference
    val gson = Gson()

    fun addNewValue(user: User) {
        val messagesReference = database.child("requestsTest")

        messagesReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val userRefs = dataSnapshot.children.mapNotNull { it.ref }
                val userRef = userRefs.find { it.ref.key == user.id }

                if (userRef != null && !user.id.isNullOrBlank()) {
                    updateLocation(user, userRef)
                } else {
                    pushRequest(user)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle errors here.
                println("hajde: error: $databaseError")
            }
        })
    }

    fun pushRequest(user: User) {
        val messageJson = gson.toJson(user)
        user.id?.let {
            database.child("requestsTest").child(it).setValue(messageJson)
        }
    }

    fun updateLocation(user: User, currentUserRef: DatabaseReference) {
        user.location.add(Location("hh33", "123123"))
        val messageJson = gson.toJson(user)
        currentUserRef.setValue(messageJson)
    }
}