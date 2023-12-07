package com.example.littlelemonmkiii.utils

fun formatPrice(price: Double, currency: String): String {
    return String.format("%.2f $currency", price)
}