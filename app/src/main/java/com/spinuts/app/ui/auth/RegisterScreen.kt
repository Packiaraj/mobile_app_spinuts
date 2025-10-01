package com.spinuts.app.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.spinuts.app.R

@Composable
fun RegisterScreen(
    onBack: () -> Unit,
    onRegistered: () -> Unit
) {
    val name = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text(stringResource(id = R.string.spinuts_register_title))
        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text(stringResource(id = R.string.spinuts_name)) }
        )
        OutlinedTextField(
            value = phone.value,
            onValueChange = { phone.value = it },
            label = { Text(stringResource(id = R.string.spinuts_phone)) }
        )
        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text(stringResource(id = R.string.spinuts_password)) },
            visualTransformation = PasswordVisualTransformation()
        )
        Button(onClick = onRegistered, enabled =
            name.value.isNotBlank() && phone.value.isNotBlank() && password.value.length >= 4
        ) {
            Text(stringResource(id = R.string.spinuts_register))
        }
        TextButton(onClick = onBack) {
            Text(stringResource(id = R.string.spinuts_back))
        }
    }
}
