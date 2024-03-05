package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Product
import com.example.myapplication.data.ProductRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SharedViewModel(val app: Application) : AndroidViewModel(app) {

    private var productRepository: ProductRepository = ProductRepository(app)

    val selectedProduct: MutableLiveData<Product> = MutableLiveData()

    val quantity: LiveData<Int?> =
        productRepository.getTotalQuantity().asLiveData()

    val productsState: StateFlow<ProductsUiState> =
        productRepository.getProducts()
            .map(ProductsUiState::Success)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = ProductsUiState.Loading,
            )

    fun loadProducts() {
        viewModelScope.launch {
            productRepository.loadProducts()
        }
    }

    fun incrementQuantity() {
        selectedProduct.value?.let { product ->
            viewModelScope.launch {
                product.quantity++
                productRepository.updateProduct(product)
            }
        }
    }

    fun decrementQuantity() {
        selectedProduct.value?.let { product ->
            viewModelScope.launch {
                if (product.quantity > 0) {
                    product.quantity--
                    productRepository.updateProduct(product)
                }
            }
        }
    }
}

sealed interface ProductsUiState {
    data object Loading : ProductsUiState

    data class Success(
        val products: List<Product>,
    ) : ProductsUiState
}
