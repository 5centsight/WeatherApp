package com.pets.weatherapp.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ErrorSnackBar(state: SnackbarHostState) {
    SnackbarHost(hostState = state) { data ->
        Snackbar(
            modifier = Modifier.padding(16.dp),
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
            contentColor = MaterialTheme.colorScheme.surface
        ) { Text(text = data.visuals.message) }
    }
}