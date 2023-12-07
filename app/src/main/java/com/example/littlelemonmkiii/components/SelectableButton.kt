package com.example.littlelemonmkiii.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SelectableButton(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    onSelected: () -> Unit,
    onUnselected: () -> Unit
) {
    Button(
        onClick = {
            if (isSelected) {
                onUnselected()
            } else {
                onSelected()
            }
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isSelected) Color.Yellow else Color.Gray
        ),
        modifier = modifier.padding(horizontal = 6.dp),
        shape = RoundedCornerShape(50)
    ) {
        Text(
            text = text,
            color = Color.White
        )
    }
}