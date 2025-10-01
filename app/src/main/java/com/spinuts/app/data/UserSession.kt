package com.spinuts.app.data

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

object UserSession {
    private val _loggedIn = mutableStateOf(false)
    val loggedIn: State<Boolean> get() = _loggedIn

    fun login() { _loggedIn.value = true }
    fun logout() { _loggedIn.value = false }
}
