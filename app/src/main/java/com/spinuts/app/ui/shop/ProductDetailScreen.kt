package com.spinuts.app.ui.shop

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.spinuts.app.data.Product
import com.spinuts.app.ui.cart.CartViewModel
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.platform.LocalConfiguration
import com.spinuts.app.R

@Composable
fun ProductDetailScreen(
    product: Product,
    cartVm: CartViewModel,
    onBack: () -> Unit
) {
    val name = localizedName(product)
    val addMessage = stringResource(R.string.spinuts_added_to_bag, name)
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = stringResource(
                R.string.spinuts_product_meta,
                product.unit,
                product.category
            ),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = stringResource(R.string.spinuts_price_format, product.price),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(24.dp))
        Button(onClick = {
            cartVm.addToCart(addMessage)
        }, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.spinuts_add_to_bag))
        }
        Spacer(Modifier.height(12.dp))
        OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.spinuts_back))
        }
    }
}

@Composable
private fun localizedName(p: Product): String {
    val lang = LocalConfiguration.current.locales[0]?.language ?: "en"
    return if (lang == "ta") p.nameTa else p.nameEn
}
