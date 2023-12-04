package com.example.littlelemonmkiii.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.littlelemonmkiii.R
import com.example.littlelemonmkiii.storage.StorageUtil
import com.example.littlelemonmkiii.navigations.*

@Composable
fun Profile(navController: NavHostController) {
    val context = LocalContext.current
    val storageUtil = StorageUtil(context)

    val firstName = storageUtil.getFirstName() ?: ""
    val lastName = storageUtil.getLastName() ?: ""
    val email = storageUtil.getEmail() ?: ""

    val firstNameState = remember { mutableStateOf(TextFieldValue(firstName)) }
    val lastNameState = remember { mutableStateOf(TextFieldValue(lastName)) }
    val emailState = remember { mutableStateOf(TextFieldValue(email)) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Spacer(modifier = Modifier.width(32.dp))

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Personal information")
            TextField(
                value = firstNameState.value,
                onValueChange = {
                    firstNameState.value = it
                },
                label = {
                    Text("First name")
                },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true
            )
            TextField(
                value = lastNameState.value,
                onValueChange = {
                    lastNameState.value = it
                },
                label = {
                    Text("Last name")
                },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true
            )
            TextField(
                value = emailState.value,
                onValueChange = {
                    emailState.value = it
                },
                label = {
                    Text("Email")
                },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Button(onClick = {
            storageUtil.clearProfileData()
            navController.navigate(Onboarding.route)
        }) {
            Text("Log out")
        }

        Spacer(modifier = Modifier.width(32.dp))
    }
}

@Preview
@Composable
fun ProfilePreview() {
    Profile(rememberNavController())
}