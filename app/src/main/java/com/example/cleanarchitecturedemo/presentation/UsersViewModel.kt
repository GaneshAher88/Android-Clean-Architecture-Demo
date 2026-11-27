package com.example.cleanarchitecturedemo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecturedemo.domain.model.User
import com.example.cleanarchitecturedemo.domain.usecase.GetDirectoryUsersUseCase
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface UsersUiState {
    data object Loading : UsersUiState
    data object Error : UsersUiState
    data class Loaded(val users: List<User>) : UsersUiState
}

class UsersViewModel(private val getDirectoryUsers: GetDirectoryUsersUseCase) : ViewModel() {
    private val _state = MutableStateFlow<UsersUiState>(UsersUiState.Loading)
    val state = _state.asStateFlow()

    init {
        loadUsers()
    }

    fun retry() {
        if (_state.value == UsersUiState.Error) {
            loadUsers()
        }
    }

    private fun loadUsers() {
        _state.value = UsersUiState.Loading
        viewModelScope.launch {
            _state.value = try {
                UsersUiState.Loaded(getDirectoryUsers())
            } catch (exception: CancellationException) {
                throw exception
            } catch (_: Exception) {
                UsersUiState.Error
            }
        }
    }
}
