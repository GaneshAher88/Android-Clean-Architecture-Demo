package com.example.cleanarchitecturedemo.domain.repository

import com.example.cleanarchitecturedemo.domain.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
}

