package com.example.cleanarchitecturedemo.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cleanarchitecturedemo.R
import com.example.cleanarchitecturedemo.domain.model.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersScreen(state: UsersUiState, onRetry: () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(stringResource(R.string.users_title)) })
    }) { insets ->
        Box(
            modifier = Modifier.fillMaxSize().padding(insets),
            contentAlignment = Alignment.Center,
        ) {
            when (state) {
                UsersUiState.Loading -> CircularProgressIndicator()
                UsersUiState.Error -> Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Text(stringResource(R.string.users_load_error), textAlign = TextAlign.Center)
                    Button(onClick = onRetry) { Text(stringResource(R.string.retry)) }
                }
                is UsersUiState.Loaded -> {
                    if (state.users.isEmpty()) {
                        Text(stringResource(R.string.users_empty))
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            items(state.users, key = { it.id }) { user ->
                                UserCard(user)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun UserCard(user: User) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(user.name, style = MaterialTheme.typography.titleMedium)
            Text(
                stringResource(R.string.user_handle, user.username),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelLarge,
            )
            Text(user.email, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
