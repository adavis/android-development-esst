package com.example.myapplication.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.SharedViewModel
import com.example.myapplication.compose.home.HomeScreen
import com.example.myapplication.compose.shop.ProductScreen
import com.example.myapplication.compose.shop.ShopScreen
import com.example.myapplication.compose.tours.ToursScreen
import com.example.myapplication.data.Product

@Composable
fun TwoTreesNavHost(
    viewModel: SharedViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.productsState.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen()
        }
        composable(route = Screen.Tours.route) {
            ToursScreen()
        }
        composable(route = Screen.Shop.route) {
            ShopScreen(
                uiState = uiState,
                onProductClick = { product: Product ->
                    viewModel.selectedProduct.value = product
                    navController.navigate(Screen.Product.route)
                }
            )
        }
        composable(route = Screen.Product.route) {
            viewModel.selectedProduct.value?.let { product ->
                ProductScreen(
                    product,
                    incrementQuantityClick = {
                        viewModel.incrementQuantity()
                    },
                    decrementQuantityClick = {
                        viewModel.decrementQuantity()
                    }
                )
            }
        }
    }
}
