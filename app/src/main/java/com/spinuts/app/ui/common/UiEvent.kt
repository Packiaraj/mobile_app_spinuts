package com.spinuts.app.ui.common

sealed class UiEvent {
    data class ShowSnackbar(val message: String) : UiEvent()
}
