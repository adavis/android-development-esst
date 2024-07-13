package com.example.myapplication.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.SharedViewModel
import com.example.myapplication.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TwoTreesApp(
    viewModel: SharedViewModel,
    navController: NavHostController = rememberNavController()
) {
    LifecycleEventEffect(event = Lifecycle.Event.ON_RESUME) {
        viewModel.loadProducts()
    }

    AppTheme {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        var canNavigateBack by remember { mutableStateOf(false) }
        navController.addOnDestinationChangedListener{ controller, _, _ ->
            canNavigateBack =
                currentDestination?.route == Screen.Product.route &&
                        controller.previousBackStackEntry != null
        }

        Scaffold(
            topBar = {
                TwoTreesAppBar(
                    canNavigateBack = canNavigateBack,
                    navigateUp = { navController.navigateUp() },
                    scrollBehavior = scrollBehavior,
                )
            },
            bottomBar = {
                TwoTreesNavBar(
                    quantity = viewModel.quantity.collectAsState(initial = null).value,
                    navController = navController,
                    currentDestination = currentDestination
                )
            },
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { innerPadding ->
            TwoTreesNavHost(
                viewModel = viewModel,
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
