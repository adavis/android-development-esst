package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.compose.TwoTreesNavHost
import com.example.myapplication.compose.navigateSingleTopTo
import com.example.myapplication.compose.screens
import com.example.myapplication.ui.theme.AppTheme
import com.example.myapplication.ui.theme.ColorAccent
import com.example.myapplication.ui.theme.ColorGrey
import com.example.myapplication.ui.theme.ColorPrimary

class MainComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TwoTreesApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TwoTreesApp() {
    AppTheme {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        val navController = rememberNavController()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(stringResource(id = R.string.app_name))
                    },
                    colors = TopAppBarColors(
                        containerColor = ColorPrimary,
                        titleContentColor = Color.White,
                        scrolledContainerColor = ColorPrimary,
                        navigationIconContentColor = Color.White,
                        actionIconContentColor = Color.White
                    ),
                    scrollBehavior = scrollBehavior
                )
            },
            bottomBar = {
                NavigationBar(
                    containerColor = ColorPrimary,
                    contentColor = Color.White
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    screens.forEach { screen ->
                        val label = stringResource(id = screen.labelResourceId)
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    painter = painterResource(id = screen.iconResourceId),
                                    contentDescription = label
                                )
                            },
                            label = {
                                Text(
                                    text = label,
                                    color = Color.White
                                )
                            },
                            colors = NavigationBarItemColors(
                                selectedIconColor = ColorAccent,
                                unselectedIconColor = Color.White,
                                selectedTextColor = Color.White,
                                selectedIndicatorColor = Color.White,
                                unselectedTextColor = Color.White,
                                disabledIconColor = ColorGrey,
                                disabledTextColor = ColorGrey
                            ),
                            selected = currentDestination?.hierarchy?.any {
                                it.route == screen.route
                            } == true,
                            onClick = {
                                navController.navigateSingleTopTo(screen.route)
                            }
                        )
                    }
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { innerPadding ->
            TwoTreesNavHost(
                navController = navController, modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
