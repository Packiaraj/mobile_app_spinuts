package com.spinuts.app.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable
fun OrderConfirmScreen(orderId: String, onSeeOrders: () -> Unit) {
    Column(Modifier.padding(16.dp)) {
        Text("Order Placed!", style = MaterialTheme.typography.headlineSmall)
        Text("Order ID: " + orderId)
        Spacer(Modifier.height(12.dp))
        Button(onClick = onSeeOrders) { Text("View My Orders") }
    }
}
