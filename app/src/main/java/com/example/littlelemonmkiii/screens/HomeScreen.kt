package com.example.littlelemonmkiii.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemonmkiii.R


@Composable
fun Home() {
    val name = "Little Lemon"
    val city = "Chicago"
    val descriptionInfo = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist"
    val backgroundColor = Color(0xFF495E57)
    var searchText by remember { mutableStateOf("") }

    val colorH1 = Color(0xFFF4CE14)
    val colorCaption = Color(0xFFEDEFEE)
    val colorBody = Color(0xFFEDEFEE)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Spacer(modifier = Modifier.width(32.dp))

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(357.dp)
                .background(backgroundColor)
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = name,
                    style = TextStyle(
                        fontSize = 30.sp, // Adjust as needed for H1
                        color = colorH1
                    )
                )
                Text(
                    text = city,
                    style = TextStyle(
                        fontSize = 18.sp, // Typical caption size
                        color = colorCaption
                    )
                )
                Text(
                    text = descriptionInfo,
                    style = TextStyle(
                        fontSize = 16.sp, // Typical body text size
                        color = colorBody
                    )
                )

//                Spacer(modifier = Modifier.fillMaxHeight())

                OutlinedTextField(
                    value = searchText,
                    onValueChange = { newText ->
                        searchText = newText
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Search") },
                    singleLine = true
                )

                Spacer(modifier = Modifier.width(16.dp))
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        // Order for delivery section

        // MenuItems section

    }
}

@Preview
@Composable
fun HomePreview() {
    MaterialTheme {
        Home()
    }
}