package com.spinuts.app.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable
fun HomeScreen(onBrowse: () -> Unit, onCart: () -> Unit) {
    Column(Modifier.padding(16.dp)) {
        Text("Welcome to Spinuts", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(12.dp))
        Button(onClick = onBrowse) { Text("Browse Products") }
        Spacer(Modifier.height(8.dp))
        OutlinedButton(onClick = onCart) { Text("Open Cart") }
    }
}
