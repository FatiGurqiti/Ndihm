package com.example.denko.data.local.preferences

import android.content.SharedPreferences

class NdihdenPreferences(private val sharedPreferences: SharedPreferences) {

    private inline fun commit(block: SharedPreferences.Editor.() -> Unit) {
        sharedPreferences
            .edit()
            .apply(block)
            .commit()
    }

    fun setString(key: String, value: String) {
        commit { putString(key, value) }
    }

    fun getString(key: String, default: String?) = sharedPreferences.getString(key, default)

    fun setBoolean(key: String, value: Boolean) {
        commit { putBoolean(key, value) }
    }

    fun getBoolean(key: String, default: Boolean) = sharedPreferences.getBoolean(key, default)
}