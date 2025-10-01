package com.spinuts.app.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun RegisterScreen(
    onBack: () -> Unit,
    onRegistered: () -> Unit
) {
    val name = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Create your account")
        OutlinedTextField(value = name.value, onValueChange = { name.value = it }, label = { Text("Name") })
        OutlinedTextField(value = phone.value, onValueChange = { phone.value = it }, label = { Text("Phone") })
        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Button(onClick = onRegistered, enabled =
            name.value.isNotBlank() && phone.value.isNotBlank() && password.value.length >= 4
        ) {
            Text("Register")
        }
        Button(onClick = onBack) {
            Text("Back")
        }
    }
}
