package com.example.denko.data.local.repository

import com.example.denko.common.Constants.PREFERENCES_USER
import com.example.denko.data.local.preferences.NdihdenPreferences
import com.example.denko.domain.model.User
import com.example.denko.domain.repository.UserRepository
import com.google.gson.Gson

class UserRepositoryImpl(
    private val ndihdenPreferences: NdihdenPreferences,
    private val gson: Gson
) : UserRepository {
    override fun getUser(): User? {
        val userString = ndihdenPreferences.getString(PREFERENCES_USER, null)
        userString?.let {
            return gson.fromJson(it, User::class.java)
        } ?: return null
    }

    override fun setUser(user: User) {
        val userString = gson.toJson(user)
        ndihdenPreferences.setString(PREFERENCES_USER, userString)
    }
}