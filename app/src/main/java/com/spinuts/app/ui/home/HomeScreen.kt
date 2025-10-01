package com.spinuts.app.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Spacer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.spinuts.app.R
import com.spinuts.app.ui.cart.CartViewModel

@Composable
fun HomeScreen(
    cartVm: CartViewModel
) {
    val navActions = LocalNav.current
    val groundnut = stringResource(id = R.string.spinuts_groundnut_oil)
    val addMessage = stringResource(id = R.string.spinuts_added_to_bag, groundnut)
    Column(Modifier.fillMaxSize().padding(24.dp)) {
        Text(
            text = stringResource(id = R.string.spinuts_home_welcome),
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(Modifier.height(8.dp))
        Text(stringResource(id = R.string.spinuts_home_snackbar_hint))
        Spacer(Modifier.height(24.dp))
        Button(onClick = { cartVm.addToCart(addMessage) }) {
            Text(stringResource(id = R.string.spinuts_home_add_groundnut))
        }
        Spacer(Modifier.height(24.dp))
        OutlinedButton(onClick = navActions.goToShop) {
            Text(stringResource(id = R.string.spinuts_home_browse_shop))
        }
    }
}
