package com.example.littlelemonmkiii.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import coil.compose.AsyncImage
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.example.littlelemonmkiii.R
import com.example.littlelemonmkiii.database.MenuItem


@Composable
fun Home(menuItems: List<MenuItem>) {
    val name = "Little Lemon"
    val city = "Chicago"
    val descriptionInfo =
        "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist"
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
                .height(250.dp)
                .background(backgroundColor)
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = name, style = TextStyle(
                        fontSize = 30.sp, // Adjust as needed for H1
                        color = colorH1
                    )
                )
                Text(
                    text = city, style = TextStyle(
                        fontSize = 18.sp, // Typical caption size
                        color = colorCaption
                    )
                )
                Text(
                    text = descriptionInfo, style = TextStyle(
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
        // Order for delivery section

        // MenuItems section
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            items(items = menuItems, itemContent = { menuItem ->
                MenuItem(
                    title = menuItem.name,
                    description = menuItem.description,
                    price = "${menuItem.price}",
                    imageUrl = menuItem.image
                )
            })
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(title: String, description: String, price: String, imageUrl: String) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.weight(2f)) {
            Text(text = title)
            Text(text = description)
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = price)
        }

        Spacer(modifier = Modifier.width(12.dp))

        // GlideImage documentation:
        // https://bumptech.github.io/glide/int/compose.html
//        GlideImage(
//            model = imageUrl,
//            contentDescription = "Dish photo: $title",
//            contentScale = ContentScale.Fit,
//            modifier = Modifier.size(72.dp)
//        )

        // Current version of GlideImage has a known problem: https://github.com/bumptech/glide/issues/5013
        // The ticket is closed but the problem remains.
        // Using Coil instead.
        // https://coil-kt.github.io/coil/compose/
        AsyncImage(
            model = imageUrl,
            contentDescription = "Dish photo: $title",
            modifier = Modifier
                .background(Color(0xFFF4CE14))
                .weight(0.8f)
                .fillMaxWidth()
                .sizeIn(minHeight = 100.dp, maxHeight = 100.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
fun HomePreview() {
    MaterialTheme {
        Home(emptyList())
    }
}

@Preview
@Composable
fun MenuItemPreview() {
    MaterialTheme {
        MenuItem(
            title = "Greek Salad",
            description = "The famous greek salad of crispy lettuce, peppers, olives, our Chicago.",
            price = "10",
            imageUrl = ""
        )
    }
}