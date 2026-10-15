package com.example.dessertclicker.ui

import androidx.annotation.DrawableRes

/** All the information needed to display the current sale. */
data class DessertUiState(
    val revenue: Int = 0,
    val dessertsSold: Int = 0,
    val currentDessertPrice: Int,
    @DrawableRes val currentDessertImageId: Int
)
