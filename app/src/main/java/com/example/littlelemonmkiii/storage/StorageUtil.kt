package com.example.littlelemonmkiii.storage

import android.content.Context

enum class UserInfo(val value: String) {
    FirstName("firstName"),
    LastName("lastName"),
    Email("email");
}

class StorageUtil(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun saveOnboardingData(firstName: String, lastName: String, email: String) {
        sharedPreferences.edit()
            .putString(UserInfo.FirstName.value, firstName)
            .putString(UserInfo.LastName.value, lastName)
            .putString(UserInfo.Email.value, email)
            .apply()
    }

    fun getFirstName(): String? {
        return sharedPreferences.getString(UserInfo.FirstName.value, null)
    }

    fun getLastName(): String? {
        return sharedPreferences.getString(UserInfo.LastName.value, null)
    }

    fun getEmail(): String? {
        return sharedPreferences.getString(UserInfo.Email.value, null)
    }

    fun shouldSkipOnboarding(): Boolean {
        val firstName = getFirstName() ?: ""
        val lastName = getLastName() ?: ""
        val email = getEmail() ?: ""

        return firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty()
    }

    fun clearProfileData() {
        sharedPreferences.edit().apply {
            remove(UserInfo.FirstName.value)
            remove(UserInfo.LastName.value)
            remove(UserInfo.Email.value)
            apply()
        }
    }
}