package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/** Owns the sales state so it survives recreation of the activity. */
class DessertViewModel : ViewModel() {
    private val desserts = Datasource.dessertList
    private val _uiState = MutableStateFlow(
        DessertUiState(
            currentDessertPrice = desserts.first().price,
            currentDessertImageId = desserts.first().imageId
        )
    )
    val uiState = _uiState.asStateFlow()

    fun onDessertClicked() {
        _uiState.update { currentState ->
            val dessertsSold = currentState.dessertsSold + 1
            val nextDessert = desserts.last {
                dessertsSold >= it.startProductionAmount
            }
            currentState.copy(
                revenue = currentState.revenue + currentState.currentDessertPrice,
                dessertsSold = dessertsSold,
                currentDessertPrice = nextDessert.price,
                currentDessertImageId = nextDessert.imageId
            )
        }
    }
}
