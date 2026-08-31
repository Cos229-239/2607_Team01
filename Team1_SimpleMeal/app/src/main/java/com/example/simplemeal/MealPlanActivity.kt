package com.example.simplemeal

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MealPlanActivity : ComponentActivity() {

    private val apiService: MealApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealApiService::class.java)
    }

    // Reactive Compose state variables
    private var mealTitle by mutableStateOf("")
    private var mealCategory by mutableStateOf("")
    private var mealInstructions by mutableStateOf("")
    private var imageUrl by mutableStateOf<String?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MealPlanScreen(
                mealTitle = mealTitle,
                mealCategory = mealCategory,
                mealInstructions = mealInstructions,
                imageUrl = imageUrl,
                onSearchClick = { query -> fetchMeal(query) },
                onRandomClick = { fetchRandomMeal() }
            )
        }

        // Initial fetch
        fetchRandomMeal()
    }

    private fun fetchMeal(query: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.searchMeals(query)
                val meal = response.meals?.firstOrNull()
                withContext(Dispatchers.Main) {
                    if (meal != null) {
                        displayMeal(meal)
                    } else {
                        Toast.makeText(this@MealPlanActivity, "No meal found!", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MealPlanActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun fetchRandomMeal() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.getRandomMeal()
                val meal = response.meals?.firstOrNull()
                withContext(Dispatchers.Main) {
                    meal?.let { displayMeal(it) }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MealPlanActivity, "Error loading meal", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun displayMeal(meal: Meal) {
        mealTitle = meal.strMeal ?: ""
        mealCategory = "${meal.strCategory ?: ""} | ${meal.strArea ?: ""}"
        mealInstructions = meal.strInstructions ?: ""
        imageUrl = meal.strMealThumb
    }
}