package com.spinuts.app.ui.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.spinuts.app.R

@Composable
fun AuthLandingScreen(
    onLogin: () -> Unit,
    onRegister: () -> Unit,
    onGuest: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.spinuts_auth_title),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = stringResource(id = R.string.spinuts_auth_subtitle),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )
        Button(onClick = onLogin, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(id = R.string.spinuts_login))
        }
        OutlinedButton(onClick = onRegister, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(id = R.string.spinuts_register))
        }
        TextButton(onClick = onGuest) {
            Text(stringResource(id = R.string.spinuts_continue_guest))
        }
    }
}
