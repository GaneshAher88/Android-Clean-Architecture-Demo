package com.example.cleanarchitecturedemo.domain.model

data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val isVerified: Boolean,
)
