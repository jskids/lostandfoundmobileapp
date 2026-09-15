package com.example.campuslostfound.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.campuslostfound.ui.home.HomeScreen

/**
 * Centralized definition of all screens and routes in the application.
 */
sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object LostItems : Screen("lost_items")
    object FoundItems : Screen("found_items")
    object ItemDetails : Screen("item_details/{itemId}") {
        fun createRoute(itemId: String): String = "item_details/$itemId"
    }
    object Profile : Screen("profile")
}

/**
 * Main application navigation host.
 * Configures Jetpack Compose Navigation and manages transitions between screens.
 */
@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = Screen.Login.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.Login.route) {
            PlaceholderScreen(name = "Login")
        }
        composable(Screen.Register.route) {
            PlaceholderScreen(name = "Register")
        }
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.LostItems.route) {
            PlaceholderScreen(name = "Lost Items")
        }
        composable(Screen.FoundItems.route) {
            PlaceholderScreen(name = "Found Items")
        }
        composable(
            route = Screen.ItemDetails.route,
            arguments = listOf(navArgument("itemId") { type = NavType.StringType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId") ?: "Unknown"
            PlaceholderScreen(name = "Item Details (ID: $itemId)")
        }
        composable(Screen.Profile.route) {
            PlaceholderScreen(name = "Profile")
        }
    }
}

/**
 * A minimal temporary placeholder screen used during compilation and development.
 */
@Composable
private fun PlaceholderScreen(
    name: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$name Screen (Placeholder)",
            style = MaterialTheme.typography.titleLarge
        )
    }
}
