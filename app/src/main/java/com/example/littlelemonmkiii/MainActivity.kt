package com.example.littlelemonmkiii

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.rememberNavController
import com.example.littlelemonmkiii.navigations.Navigation
import com.example.littlelemonmkiii.storage.StorageUtil
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json

class MainActivity : AppCompatActivity() {
    private val storageUtil: StorageUtil by lazy {
        StorageUtil(context = applicationContext)
    }

    private val listUrl = "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val skipOnboarding = storageUtil.shouldSkipOnboarding()
            Navigation(navController = navController, skipOnboarding = skipOnboarding)
        }
    }
}