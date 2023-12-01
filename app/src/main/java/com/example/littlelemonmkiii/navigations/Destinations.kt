package com.example.littlelemonmkiii.navigations

interface Destinations {
    val route: String
}

object Home : Destinations {
    override val route = "Home"
}

object Profile : Destinations {
    override val route = "Profile"
}

object Onboarding : Destinations {
    override val route = "Onboarding"
}