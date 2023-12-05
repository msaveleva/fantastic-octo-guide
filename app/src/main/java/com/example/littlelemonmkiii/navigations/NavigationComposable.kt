package com.example.littlelemonmkiii.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.littlelemonmkiii.database.MenuItem
import com.example.littlelemonmkiii.screens.Home
import com.example.littlelemonmkiii.screens.Onboarding
import com.example.littlelemonmkiii.screens.Profile

@Composable
fun Navigation(
    navController: NavHostController,
    skipOnboarding: Boolean,
    menuItems: List<MenuItem>
) {
    val startingRoute = if (skipOnboarding) { Home.route } else { Onboarding.route }

    NavHost(navController = navController, startDestination = startingRoute) {
        composable(Onboarding.route) { Onboarding(navController) }
        composable(Home.route) { Home(menuItems) }
        composable(Profile.route) { Profile(navController) }
    }
}