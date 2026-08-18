package com.example.simplemeal

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MealPlanScreen(

    onSearchClick: (String) -> Unit = {},
    onRandomClick: () -> Unit = {},
    mealTitle: String = "",
    mealCategory: String = "",
    mealInstructions: String = "",
    imageUrl: String? = null
) {
    var searchQuery by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search meal (e.g., pasta)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { onSearchClick(searchQuery) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Search")
                }

                Button(
                    onClick = { onRandomClick() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Random Meal")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            if (mealTitle.isNotEmpty()) {
                Text(
                    text = mealTitle,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
            }


            if (mealCategory.isNotEmpty()) {
                Text(
                    text = mealCategory,
                    color = Color(0xFF666666),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
            }


            if (mealInstructions.isNotEmpty()) {
                Text(
                    text = mealInstructions,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 6
                )
            }
        }
    }
}