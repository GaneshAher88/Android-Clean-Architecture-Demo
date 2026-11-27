package com.example.cleanarchitecturedemo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.cleanarchitecturedemo.data.RetrofitUserRepository
import com.example.cleanarchitecturedemo.data.createUserApi
import com.example.cleanarchitecturedemo.domain.usecase.GetDirectoryUsersUseCase
import com.example.cleanarchitecturedemo.ui.theme.UserDirectoryTheme

class MainActivity : ComponentActivity() {
    private val viewModel: UsersViewModel by viewModels {
        viewModelFactory {
            initializer {
                val repository = RetrofitUserRepository(createUserApi())
                UsersViewModel(GetDirectoryUsersUseCase(repository))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val state by viewModel.state.collectAsStateWithLifecycle()
            UserDirectoryTheme {
                UsersScreen(state, onRetry = viewModel::retry)
            }
        }
    }
}
