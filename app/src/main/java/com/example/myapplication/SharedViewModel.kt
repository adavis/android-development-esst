package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.Product
import com.example.myapplication.data.ProductRepository
import kotlinx.coroutines.launch

class SharedViewModel(val app: Application) : AndroidViewModel(app) {

    private var productRepository: ProductRepository = ProductRepository(app)

    val selectedProduct: MutableLiveData<Product> = MutableLiveData()

    val products: LiveData<List<Product>> =
        productRepository.getProducts().asLiveData()

    val quantity: LiveData<Int?> =
        productRepository.getTotalQuantity().asLiveData()

    init {
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
