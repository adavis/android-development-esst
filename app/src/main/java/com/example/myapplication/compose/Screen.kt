package com.example.myapplication.compose

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myapplication.R

sealed class Screen(
    val route: String,
    @StringRes val labelResourceId: Int,
    val icon: ImageVector
) {
    data object Home : Screen("home", R.string.home_label, Icons.Filled.Home)
    data object Tours : Screen("tours", R.string.tours_label, Icons.Filled.Place)
    data object Shop : Screen("shop", R.string.shop_label, Icons.Filled.ShoppingCart)

    // this is a nested screen
    data object Product : Screen("product", 0, Icons.Filled.ShoppingCart)
}

val screens = listOf(Screen.Home, Screen.Tours, Screen.Shop)
