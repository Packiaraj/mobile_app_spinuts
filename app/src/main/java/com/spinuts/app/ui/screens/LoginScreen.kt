package com.spinuts.app.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
@Composable
fun LoginScreen(onLoggedIn: () -> Unit) {
    var phone by remember { mutableStateOf("") }
    Column(Modifier.padding(16.dp)) {
        Text("Spinuts", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone (10 digits)") })
        Spacer(Modifier.height(12.dp))
        Button(onClick = { Firebase.auth.signInAnonymously().addOnSuccessListener { onLoggedIn() } }, enabled = phone.length >= 10) { Text("Continue") }
        Text("(Demo uses guest login. Enable real OTP in Firebase.)", style = MaterialTheme.typography.bodySmall)
    }
}
