package com.spinuts.app.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
@Composable
fun ProductDetailScreen(productId: String, onAddToCart: () -> Unit) {
    var p by remember { mutableStateOf<ProductItem?>(null) }
    var qty by remember { mutableStateOf(1) }
    LaunchedEffect(productId) {
        Firebase.firestore.collection("products").document(productId).get()
            .addOnSuccessListener { d -> p = d.toObject(ProductItem::class.java)?.copy(id = d.id) }
    }
    p?.let { prod ->
        Column(Modifier.padding(16.dp)) {
            Text(prod.name_en.ifBlank { prod.name_ta }, style = MaterialTheme.typography.headlineSmall)
            Text(prod.unit)
            Text("₹" + (prod.sale_price ?: prod.price))
            Spacer(Modifier.height(12.dp))
            Row { Button(onClick = { if (qty > 1) qty-- }) { Text("-") }; Spacer(Modifier.width(8.dp)); Text("$qty"); Spacer(Modifier.width(8.dp)); Button(onClick = { qty++ }) { Text("+") } }
            Spacer(Modifier.height(12.dp))
            Button(onClick = onAddToCart) { Text("Add to Cart (demo)") }
        }
    } ?: CircularProgressIndicator()
}
