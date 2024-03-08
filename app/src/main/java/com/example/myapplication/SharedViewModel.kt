package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Product
import com.example.myapplication.data.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SharedViewModel(app: Application) : AndroidViewModel(app) {

    private var productRepository: ProductRepository = ProductRepository(app)

    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct: StateFlow<Product?> get() = _selectedProduct

    val quantity: Flow<Int?> = productRepository.getTotalQuantity()

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

    fun selectProduct(product: Product) {
        _selectedProduct.value = product
    }

    fun incrementQuantity() {
        _selectedProduct.value?.let { product ->
            viewModelScope.launch {
                product.quantity++
                productRepository.updateProduct(product)
            }
        }
    }

    fun decrementQuantity() {
        _selectedProduct.value?.let { product ->
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
