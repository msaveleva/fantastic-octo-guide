package com.example.littlelemonmkiii

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.rememberNavController
import com.example.littlelemonmkiii.navigations.Navigation
import com.example.littlelemonmkiii.storage.StorageUtil

class MainActivity : AppCompatActivity() {
    private val storageUtil: StorageUtil by lazy {
        StorageUtil(context = applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val skipOnboarding = storageUtil.skipOnboarding()
            Navigation(navController = navController, skipOnboarding = skipOnboarding)
        }
    }
}˚