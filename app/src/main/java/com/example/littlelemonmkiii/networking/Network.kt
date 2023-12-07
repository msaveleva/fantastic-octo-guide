package com.example.littlelemonmkiii.networking

import com.example.littlelemonmkiii.database.MenuItem
import kotlinx.serialization.Serializable

@Serializable
data class MenuNetwork(
    val menu: List<MenuItemNetwork>
)

@Serializable
data class MenuItemNetwork(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
) {
    fun toMenuItemRoom() = MenuItem(
        id = id,
        title = title,
        description = description,
        price = price.toDouble(),
        image = image,
        category = category
    )
}