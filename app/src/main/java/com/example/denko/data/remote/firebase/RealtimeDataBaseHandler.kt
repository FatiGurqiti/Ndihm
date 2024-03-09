package com.example.denko.data.remote.firebase

import com.example.denko.domain.model.User
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson

class RealtimeDataBaseHandler {
    private val gson = Gson() //TODO("Inject this")

    companion object {
        private const val PATH = "requestsTest"
    }

    fun addNewValue(user: User) {
        val database = FirebaseDatabase.getInstance().reference
        val dbReference = database.child(PATH)
        val messageJson = gson.toJson(user)
        user.id?.let {
            dbReference.child(it).setValue(messageJson)
        }
    }
}