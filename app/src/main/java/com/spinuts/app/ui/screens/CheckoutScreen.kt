package com.spinuts.app.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable
fun CheckoutScreen(onPlaced: (String) -> Unit) {
    var address by remember { mutableStateOf("") }
    var pincode by remember { mutableStateOf("") }
    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(address, { address = it }, label = { Text("Address") })
        OutlinedTextField(pincode, { pincode = it }, label = { Text("PIN code") })
        Spacer(Modifier.height(12.dp))
        Button(onClick = { onPlaced("ORDER-DEMO-001") }, enabled = address.isNotBlank() && pincode.length >= 6) { Text("Place Order (demo)") }
        Text("(Wire to Firestore in production)", style = MaterialTheme.typography.bodySmall)
    }
}
