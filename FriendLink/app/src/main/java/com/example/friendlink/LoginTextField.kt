package com.example.friendlink

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun LoginTextField(
    modifier: Modifier = Modifier,
    label: String
) {
    TextField(
        modifier = modifier,
        value = "",
        onValueChange = {},
        label = {
            Text(text = label, style = MaterialTheme.typography.labelMedium, color = Color.Black)
        },
        colors = TextFieldDefaults.colors(
            unfocusedPlaceholderColor = Color(0xFF94A3B8),
            focusedPlaceholderColor = Color.Black,
            unfocusedContainerColor = Color(0xFFF1F5F9),
            focusedContainerColor = Color(0xFFF1F5F9)
        )
    )
}