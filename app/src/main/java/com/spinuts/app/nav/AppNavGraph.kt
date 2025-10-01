package com.spinuts.app.nav

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.spinuts.app.R
import com.spinuts.app.data.ProductRepo
import com.spinuts.app.data.UserSession
import com.spinuts.app.ui.auth.AuthLandingScreen
import com.spinuts.app.ui.auth.LoginScreen
import com.spinuts.app.ui.auth.RegisterScreen
import com.spinuts.app.ui.cart.CartViewModel
import com.spinuts.app.ui.common.UiEvent
import com.spinuts.app.ui.home.HomeScreen
import com.spinuts.app.ui.home.LocalNav
import com.spinuts.app.ui.home.NavActions
import com.spinuts.app.ui.shop.ProductDetailScreen
import com.spinuts.app.ui.shop.ShopScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val snackHost = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val cartVm: CartViewModel = viewModel()
    val loggedIn by UserSession.loggedIn

    LaunchedEffect(cartVm) {
        cartVm.events.collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scope.launch { snackHost.showSnackbar(event.message) }
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackHost) }
    ) { inner ->
        NavHost(
            navController = navController,
            startDestination = if (loggedIn) Routes.HOME else Routes.AUTH,
            modifier = Modifier.padding(inner)
        ) {
            composable(Routes.AUTH) {
                AuthLandingScreen(
                    onLogin = { navController.navigate(Routes.LOGIN) },
                    onRegister = { navController.navigate(Routes.REGISTER) },
                    onGuest = {
                        UserSession.login()
                        navController.navigate(Routes.HOME) {
                            popUpTo(Routes.AUTH) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(Routes.LOGIN) {
                LoginScreen(
                    onBack = { navController.popBackStack() },
                    onLoggedIn = {
                        UserSession.login()
                        navController.navigate(Routes.HOME) {
                            popUpTo(Routes.AUTH) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(Routes.REGISTER) {
                RegisterScreen(
                    onBack = { navController.popBackStack() },
                    onRegistered = {
                        UserSession.login()
                        navController.navigate(Routes.HOME) {
                            popUpTo(Routes.AUTH) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            }
            composable(Routes.HOME) {
                CompositionLocalProvider(
                    LocalNav provides NavActions(
                        goToShop = { navController.navigate(Routes.SHOP) }
                    )
                ) {
                    HomeScreen(cartVm = cartVm)
                }
            }
            composable(Routes.SHOP) {
                ShopScreen(
                    cartVm = cartVm,
                    onOpenDetail = { id -> navController.navigate(Routes.productDetail(id)) }
                )
            }
            composable(Routes.PRODUCT_DETAIL) { backStack ->
                val id = backStack.arguments?.getString("productId") ?: return@composable
                val product = ProductRepo.byId(id)
                if (product != null) {
                    ProductDetailScreen(
                        product = product,
                        cartVm = cartVm,
                        onBack = { navController.popBackStack() }
                    )
                } else {
                    Text(stringResource(id = R.string.spinuts_product_missing))
                }
            }
        }
    }
}
