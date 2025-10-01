package com.spinuts.app.ui.shop

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.spinuts.app.R
import com.spinuts.app.data.Product
import com.spinuts.app.data.ProductRepo
import com.spinuts.app.ui.cart.CartViewModel

@Composable
fun ShopScreen(
    cartVm: CartViewModel,
    onOpenDetail: (productId: String) -> Unit
){
    var query by remember { mutableStateOf("") }
    val items = remember(query) {
        if (query.isBlank()) ProductRepo.items
        else ProductRepo.items.filter { p ->
            p.nameEn.contains(query, ignoreCase = true) ||
            p.nameTa.contains(query)
        }
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text(stringResource(R.string.spinuts_search_placeholder)) },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.spinuts_shop_title),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.height(8.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(items, key = { it.id }) { p ->
                ProductRow(
                    product = p,
                    onDetail = { onOpenDetail(p.id) },
                    onAdd = {
                        cartVm.addToCart(localizedName(p))
                    }
                )
            }
        }
    }
}

@Composable
private fun ProductRow(
    product: Product,
    onDetail: () -> Unit,
    onAdd: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(Modifier.padding(12.dp)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column(Modifier.weight(1f).clickable { onDetail() }) {
                    Text(localizedName(product), fontWeight = FontWeight.Medium)
                    Text("${product.unit} • ${product.category}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary)
                }
                Text("₹${"%.2f".format(product.price)}", fontWeight = FontWeight.SemiBold)
            }
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = onDetail, modifier = Modifier.weight(1f)) {
                    Text(stringResource(R.string.spinuts_details))
                }
                Button(onClick = onAdd, modifier = Modifier.weight(1f)) {
                    Text(stringResource(R.string.spinuts_add_to_bag))
                }
            }
        }
    }
}

@Composable
private fun localizedName(p: Product): String {
    val lang = LocalConfiguration.current.locales[0]?.language ?: "en"
    return if (lang == "ta") p.nameTa else p.nameEn
}
