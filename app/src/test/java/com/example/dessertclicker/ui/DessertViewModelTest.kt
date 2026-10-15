package com.example.dessertclicker.ui

import com.example.dessertclicker.data.Datasource
import org.junit.Assert.assertEquals
import org.junit.Test

class DessertViewModelTest {
    @Test
    fun initialStateHasNoSalesAndFirstDessert() {
        val state = DessertViewModel().uiState.value
        assertEquals(0, state.revenue)
        assertEquals(0, state.dessertsSold)
        assertEquals(Datasource.dessertList.first().price, state.currentDessertPrice)
        assertEquals(Datasource.dessertList.first().imageId, state.currentDessertImageId)
    }

    @Test
    fun fifthSaleChargesCupcakeAndSelectsDonutForNextSale() {
        val viewModel = DessertViewModel()
        repeat(5) { viewModel.onDessertClicked() }
        assertEquals(5, viewModel.uiState.value.dessertsSold)
        assertEquals(25, viewModel.uiState.value.revenue)
        assertEquals(Datasource.dessertList[1].imageId,
            viewModel.uiState.value.currentDessertImageId)
        viewModel.onDessertClicked()
        assertEquals(35, viewModel.uiState.value.revenue)
    }

    @Test
    fun everyProductionBoundaryUpdatesPriceAndImage() {
        val viewModel = DessertViewModel()
        for (dessert in Datasource.dessertList) {
            while (viewModel.uiState.value.dessertsSold < dessert.startProductionAmount) {
                viewModel.onDessertClicked()
            }
            assertEquals(dessert.price, viewModel.uiState.value.currentDessertPrice)
            assertEquals(dessert.imageId, viewModel.uiState.value.currentDessertImageId)
        }
        viewModel.onDessertClicked()
        assertEquals(Datasource.dessertList.last().imageId,
            viewModel.uiState.value.currentDessertImageId)
    }
}
