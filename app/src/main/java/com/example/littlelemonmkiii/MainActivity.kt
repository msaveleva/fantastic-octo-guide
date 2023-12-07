package com.example.littlelemonmkiii

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.littlelemonmkiii.database.MenuDatabase
import com.example.littlelemonmkiii.navigations.Navigation
import com.example.littlelemonmkiii.networking.MenuItemNetwork
import com.example.littlelemonmkiii.networking.MenuNetwork
import com.example.littlelemonmkiii.storage.StorageUtil
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            MenuDatabase::class.java,
            "database")
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch(Dispatchers.IO) {
            if (database.menuDao().isEmpty()) {
                saveMenuToDatabase(getMenu())

                val saved = database.menuDao().getAll()
            }
        }

        setContent {
            val databaseMenuItems by database.menuDao().getAll().observeAsState(emptyList())

            val navController = rememberNavController()
            val skipOnboarding = storageUtil.shouldSkipOnboarding()
            Navigation(
                navController = navController,
                skipOnboarding = skipOnboarding,
                menuItems = databaseMenuItems
            )
        }
    }

    private suspend fun getMenu(): List<MenuItemNetwork> {
        return client.get(listUrl).body<MenuNetwork>().menu
    }

    private fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        database.menuDao().insertAll(*menuItemsRoom.toTypedArray())
    }
}