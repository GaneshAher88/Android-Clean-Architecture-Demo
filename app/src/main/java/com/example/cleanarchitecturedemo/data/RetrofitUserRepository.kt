package com.example.cleanarchitecturedemo.data

import com.example.cleanarchitecturedemo.domain.model.User
import com.example.cleanarchitecturedemo.domain.repository.UserRepository

class RetrofitUserRepository(private val api: UserApiInterface) : UserRepository {
    override suspend fun getUsers() = api.getUsers().map { it.toDomain() }
}

// JSONPlaceholder has no verification field. These IDs simulate verified accounts.
private val demoVerifiedUserIds = setOf(1, 3, 5)

private fun UserDto.toDomain() = User(
    id = id,
    name = name,
    username = username,
    email = email,
    isVerified = id in demoVerifiedUserIds,
)
