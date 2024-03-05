package com.example.myapplication.compose

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.myapplication.R

sealed class Screen(
    val route: String,
    @StringRes val labelResourceId: Int,
    @DrawableRes val iconResourceId: Int
) {
    data object Home : Screen("home", R.string.home_label, R.drawable.ic_home)
    data object Tours : Screen("tours", R.string.tours_label, R.drawable.ic_tour)
    data object Shop : Screen("shop", R.string.shop_label, R.drawable.ic_baseline_shopping_cart)

    // this is a nested screen
    data object Product : Screen("product", 0, 0)
}

val screens = listOf(Screen.Home, Screen.Tours, Screen.Shop)
