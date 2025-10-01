package com.spinuts.app.nav
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.spinuts.app.ui.screens.*
object Routes {
    const val LOGIN = "login"
    const val HOME = "home"
    const val LIST = "list"
    const val DETAIL = "detail/{id}"
    const val CART = "cart"
    const val CHECKOUT = "checkout"
    const val CONFIRM = "confirm/{orderId}"
    const val ORDERS = "orders"
    const val PROFILE = "profile"
}
@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController, startDestination = Routes.LOGIN) {
        composable(Routes.LOGIN) { LoginScreen(onLoggedIn = { navController.navigate(Routes.HOME) { popUpTo(0) } }) }
        composable(Routes.HOME) { HomeScreen(onBrowse = { navController.navigate(Routes.LIST) }, onCart = { navController.navigate(Routes.CART) }) }
        composable(Routes.LIST) { ProductListScreen(onOpen = { id -> navController.navigate("detail/$id") }) }
        composable("detail/{id}") { backStack ->
            val id = backStack.arguments?.getString("id") ?: ""
            ProductDetailScreen(productId = id, onAddToCart = { navController.navigate(Routes.CART) })
        }
        composable(Routes.CART) { CartScreen(onCheckout = { navController.navigate(Routes.CECKOUT) }) }
        composable(Routes.CHECKOUT) { CheckoutScreen(onPlaced = { orderId -> navController.navigate("confirm/$orderId") { popUpTo(Routes.HOME) } }) }
        composable("confirm/{orderId}") { entry ->
            OrderConfirmScreen(orderId = entry.arguments?.getString("orderId") ?: "", onSeeOrders = { navController.navigate(Routes.ORDERS) })
        }
        composable(Routes.ORDERS) { OrdersScreen() }
        composable(Routes.PROFILE) { ProfileScreen() }
    }
}
