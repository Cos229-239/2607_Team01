package com.example.simplemeal.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableStateListOf

@Composable
fun GroceryListScreen()
{
    var tomatoesChecked by remember { mutableStateOf(false) }
    var chickenChecked by remember { mutableStateOf(false) }

    var newItem by remember { mutableStateOf ("")}
    val addedItems = remember { mutableStateListOf<Pair<String, Boolean>>()}

    Column(modifier = Modifier.padding(16.dp))
    {
        Text(text = "Grocery List")

        Row {
            Checkbox(
                checked = tomatoesChecked,
                onCheckedChange = { tomatoesChecked = it }
            )

            Text(text = "2 Tomatoes")
        }

        Row {
            Checkbox(
                checked = chickenChecked,
                onCheckedChange = { chickenChecked = it }
            )

            Text(text = "1 lb Chicken Breast")
        }
        OutlinedTextField(
            value = newItem,
            onValueChange = { newItem = it },
            label = { Text("New Item") }
        )

        Button(
            onClick = {
                if (newItem.isNotBlank()) {
                    addedItems.add(Pair(newItem, false))
                    newItem = ""
                }
            }
        ) {
            Text("Add Item")
        }

        addedItems.forEachIndexed { index, item ->
            Row {
                Checkbox(
                    checked = item.second,
                    onCheckedChange = {
                        addedItems[index] = Pair(item.first, it)
                    }
                )

                Text(text = item.first)
            }
        }
    }
}