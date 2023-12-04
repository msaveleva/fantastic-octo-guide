package com.example.littlelemonmkiii.navigations

interface Destinations {
    val route: String
}

// Kotlin, an object is a singleton instance, meaning there's only one instance of
// Home throughout the application.
object Home : Destinations {
    override val route = "Home"
}

object Profile : Destinations {
    override val route = "Profile"
}

object Onboarding : Destinations {
    override val route = "Onboarding"
}