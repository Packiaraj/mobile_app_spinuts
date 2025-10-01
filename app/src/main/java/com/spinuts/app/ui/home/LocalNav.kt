package com.spinuts.app.ui.home

import androidx.compose.runtime.compositionLocalOf

/** Tiny helper so HomeScreen can navigate without bringing NavController as a parameter */
val LocalNav = compositionLocalOf<NavActions> { error("Nav actions not provided") }

data class NavActions(
    val goToShop: () -> Unit
)
