package com.example.cleanarchitecturedemo.domain.usecase

import com.example.cleanarchitecturedemo.domain.repository.UserRepository

class GetDirectoryUsersUseCase(private val repository: UserRepository) {
    suspend operator fun invoke() = repository.getUsers().filter { it.isVerified }
}
