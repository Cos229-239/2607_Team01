package com.example.simplemeal

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.example.simplemeal.ui.theme.BluePrimary
import com.example.simplemeal.ui.theme.InterDisplay
import com.example.simplemeal.ui.theme.SnowDisplay


@Composable
fun MealPlanScreen(

    onSearchClick: (String) -> Unit = {},
    onRandomClick: () -> Unit = {},
    mealTitle: String = "",
    mealCategory: String = "",
    mealInstructions: String = "",
    imageUrl: String? = null,
    onListButtonPressed: () -> Unit
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

            if (!imageUrl.isNullOrEmpty()) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Meal Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

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

            Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxSize()){

                Button(onClick = { onListButtonPressed()


                },colors = ButtonDefaults.buttonColors(
                    containerColor = BluePrimary,

                    )
                ) {
                    Text(
                        text = "Create List",
                        textAlign = TextAlign.Center,
                        fontFamily = InterDisplay,
                        fontSize = 24.sp,
                        color = SnowDisplay
                    )
                }


            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MealPlanPreview() {
    MealPlanScreen(
        mealTitle = "Pasta Primavera",
        mealCategory = "Italian | Vegetarian",
        mealInstructions = "Boil pasta. Sauté fresh vegetables in olive oil and garlic. Toss together with parmesan cheese.",
        onListButtonPressed = {}
    )
}



