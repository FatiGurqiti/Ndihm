package com.example.denko.data.remote.firebase

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.ValueEventListener

@IgnoreExtraProperties
data class User1(val username: String? = null, val email: String? = null) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}

class RealtimeDataBaseHandler {
    private var database: DatabaseReference = FirebaseDatabase.getInstance().getReference("users")

    fun addNewValue() {
        val database = FirebaseDatabase.getInstance().reference
        val message = User1("John", "Hello, world!")
        database.child("messages").push().setValue(message)

        val messagesReference = database.child("messages")
        messagesReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val messages = dataSnapshot.children.mapNotNull { it.getValue(User1::class.java) }
                // Update the UI with the new list of messages.
                println("hajde: $messages")
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // Handle errors here.
                println("hajde: error: $databaseError")
            }
        })
    }
}