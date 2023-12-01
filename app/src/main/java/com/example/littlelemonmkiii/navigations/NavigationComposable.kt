package com.example.littlelemonmkiii.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.littlelemonmkiii.screens.Home
import com.example.littlelemonmkiii.screens.Onboarding
import com.example.littlelemonmkiii.screens.Profile

@Composable
fun Navigation(navController: NavHostController) {
    // TODO: update start destination depending on user data in storage.
    NavHost(navController = navController, startDestination = Onboarding.route) {
        composable(Onboarding.route) { Onboarding() }
        composable(Home.route) { Home() }
        composable(Profile.route) { Profile() }
    }
}