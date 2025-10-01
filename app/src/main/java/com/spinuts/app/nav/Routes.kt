package com.spinuts.app.nav

object Routes {
    const val AUTH = "auth"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
    const val SHOP = "shop"
    const val PRODUCT_DETAIL = "product_detail/{productId}"

    fun productDetail(productId: String) = "product_detail/$productId"
}
