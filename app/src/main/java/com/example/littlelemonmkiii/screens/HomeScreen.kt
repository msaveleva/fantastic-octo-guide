package com.example.littlelemonmkiii.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.littlelemonmkiii.R
import com.example.littlelemonmkiii.components.SelectableButton
import com.example.littlelemonmkiii.database.MenuItem
import com.example.littlelemonmkiii.utils.formatPrice
import com.example.littlelemonmkiii.navigations.*


@Composable
fun Home(
    menuItems: List<MenuItem>,
    navController: NavHostController
) {
    val name = "Little Lemon"
    val city = "Chicago"
    val descriptionInfo =
        "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist"
    val backgroundColor = Color(0xFF495E57)
    var searchText by remember { mutableStateOf("") }

    val colorH1 = Color(0xFFF4CE14)
    val colorCaption = Color(0xFFEDEFEE)
    val colorBody = Color(0xFFEDEFEE)

    // Updating this value when needed.
    val filteredMenuItems = remember { mutableStateOf(menuItems) }
    val categoriesList = menuItems.map { it.category }.toSet()
    val selectedCategory = remember { mutableStateOf("") }

    fun menuItemsPresented(): List<MenuItem> {
        return if (searchText.isEmpty() &&
            selectedCategory.value.isEmpty()) {
            menuItems
        } else {
            filteredMenuItems.value
        }
    }

    // Affects filteredMenuItems.
    // One filtering method to combine filtering from menu and search phrase.
    fun filterMenuItems() {
        filteredMenuItems.value = menuItems

        // Filtering search phrase.
        if (searchText.isNotEmpty()) {
            filteredMenuItems.value = menuItems.filter {
                it.title.lowercase().contains(searchText.lowercase())
            }
        }

        // Filtering category.
        if (selectedCategory.value.isNotEmpty()) {
            // Filtering through filtered to consider the results of search text filtering done before.
            filteredMenuItems.value = filteredMenuItems.value.filter {
                it.category == selectedCategory.value
            }
        }
    }

    // MenuItems section
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        // Header item
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Spacer(modifier = Modifier.width(32.dp))

                Row(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .weight(1f)
                            .height(72.dp)
                    )
                    Button(
                        modifier = Modifier.padding(20.dp),
                        colors = ButtonDefaults.buttonColors(colorH1),
                        shape = RoundedCornerShape(50),
                        onClick = { 
                            navController.navigate(Profile.route)
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Person, contentDescription = "Profile")
                    }
                }

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

                        Spacer(modifier = Modifier.weight(1f))

                        OutlinedTextField(
                            value = searchText,
                            onValueChange = { newText ->
                                searchText = newText

                                filterMenuItems()
                            },
                            leadingIcon = {
                                Icon(imageVector = Icons.Default.Search, contentDescription = "")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            label = { Text("Enter search phrase") },
                            singleLine = true
                        )
                    }
                }
            }

            // Categories menu
            Column {
                Text(
                    text = "Order for delivery", style = TextStyle(
                        fontSize = 20.sp
                    ),
                    modifier = Modifier.padding(16.dp)
                )

                // State to track the currently selected index
                var selectedIndex by remember { mutableIntStateOf(-1) } // -1 for none selected

                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                ) {
                    for ((index, label) in categoriesList.withIndex()) {
                        SelectableButton(
                            text = label,
                            isSelected = selectedIndex == index,
                            onSelected = {
                                selectedIndex = index
                                selectedCategory.value = label
                                filterMenuItems()
                            },
                            onUnselected = {
                                selectedIndex = -1
                                selectedCategory.value = ""
                                filterMenuItems()
                            }
                        )
                    }
                }
            }

            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.padding(vertical = 12.dp)
            )
        }

        // Menu items
        items(items = menuItemsPresented(), itemContent = { menuItem ->
            MenuItem(
                title = menuItem.title,
                description = menuItem.description,
                price = formatPrice(menuItem.price, "€"),
                imageUrl = menuItem.image
            )
        })
    }
}

@Composable
fun MenuItem(title: String, description: String, price: String, imageUrl: String) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.weight(2f)) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Text(text = description)
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = price,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            )
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
                .weight(0.8f)
                .fillMaxWidth()
                .sizeIn(minHeight = 100.dp, maxHeight = 100.dp)
                .clip(RoundedCornerShape(15)),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
fun HomePreview() {
    MaterialTheme {
        Home(emptyList(), rememberNavController())
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