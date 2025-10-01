package com.spinuts.app.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spinuts.app.ui.common.UiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {
    private val _events = MutableSharedFlow<UiEvent>()
    val events = _events.asSharedFlow()

    fun addToCart(item: String) {
        viewModelScope.launch {
            _events.emit(UiEvent.ShowSnackbar("$item added to bag"))
        }
    }
}
