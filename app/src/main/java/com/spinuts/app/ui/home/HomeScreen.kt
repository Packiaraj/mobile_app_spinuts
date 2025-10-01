package com.spinuts.app.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.spinuts.app.ui.cart.CartViewModel

@Composable
fun HomeScreen(
    cartVm: CartViewModel
) {
    Column(Modifier.fillMaxSize().padding(24.dp)) {
        Text("Welcome to Spinuts", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))
        Text("Tap the button below to simulate adding an item to your bag. You'll see a snackbar.")
        Spacer(Modifier.height(24.dp))
        Button(onClick = { cartVm.addToCart("Groundnut Oil") }) {
            Text("Add Groundnut Oil to bag")
        }
        Spacer(Modifier.height(24.dp))
        // Simple link to the real shop screen
        OutlinedButton(onClick = { (LocalNav.goToShop)() }) {
            Text("Browse shop")
        }
    }
}
