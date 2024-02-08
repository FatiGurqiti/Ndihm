package com.example.denko.domain.repository

import com.example.denko.domain.model.User

interface UserRepository {
    fun getUser(): User?
    fun setUser(user: User)
}