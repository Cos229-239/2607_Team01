package com.example.simplemeal.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simplemeal.ProfileScreen

@Composable
fun GroceryListScreen()
{
    Column (modifier = Modifier.padding(16.dp))
    {
        Text(text = "Grocery List")
        Row()
        {
            Checkbox(checked = false, onCheckedChange = null)
            Text(text = "2 Tomatoes")
        }
        Row()
        {
            Checkbox(checked = false, onCheckedChange = null)
            Text(text = "1 lb Chicken Breast")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GroceryListPreview() {
    GroceryListScreen()
}