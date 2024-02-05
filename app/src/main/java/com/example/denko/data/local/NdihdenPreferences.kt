package com.example.denko.data.local

import android.content.SharedPreferences

class NdihdenPreferences(private val sharedPreferences: SharedPreferences) {

    private inline fun commit(block: SharedPreferences.Editor.() -> Unit) {
        sharedPreferences
            .edit()
            .apply(block)
            .commit()
    }

    fun setBoolean(key: String, default: Boolean) {
        commit { putBoolean(key, default) }
    }

    fun getBoolean(key: String, default: Boolean) = sharedPreferences.getBoolean(key, default)
}