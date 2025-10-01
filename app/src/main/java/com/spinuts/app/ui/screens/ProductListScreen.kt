package com.spinuts.app.ui.screens
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
data class ProductItem(
    val id: String = "",
    val name_en: String = "",
    val name_ta: String = "",
    val unit: String = "",
    val price: Double = 0.0,
    val sale_price: Double? = null,
    val is_active: Boolean = true
)
@Composable
fun ProductListScreen(onOpen: (String) -> Unit) {
    var items by remember { mutableStateOf(listOf<ProductItem>()) }
    var loading by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        Firebase.firestore.collection("products").whereEqualTo("is_active", true).get()
            .addOnSuccessListener { q ->
                items = q.documents.mapNotNull { it.toObject(ProductItem::class.java)?.copy(id = it.id) }
                loading = false
            }
    }
    if (loading) CircularProgressIndicator() else LazyColumn {
        items(items) { p ->
            ListItem(
                headlineContent = { Text(p.name_en.ifBlank { p.name_ta }) },
                supportingContent = { Text(p.unit + " • ₹" + (p.sale_price ?: p.price)) },
                trailingContent = { Text("View") },
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp).clickable { onOpen(p.id) }
            )
            Divider()
        }
    }
}
