package com.example.simplemeal

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MealPlanActivity : AppCompatActivity() {

    private val apiService: MealApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealApiService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_plan)

        val etSearch = findViewById<EditText>(R.id.etSearch)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val btnRandom = findViewById<Button>(R.id.btnRandom)

        btnSearch.setOnClickListener {
            val query = etSearch.text.toString().trim()
            if (query.isNotEmpty()) {
                fetchMeal(query)
            }
        }

        btnRandom.setOnClickListener {
            fetchRandomMeal()
        }

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
        findViewById<TextView>(R.id.tvMealTitle).text = meal.strMeal
        findViewById<TextView>(R.id.tvCategory).text = "${meal.strCategory ?: ""} | ${meal.strArea ?: ""}"
        findViewById<TextView>(R.id.tvInstructions).text = meal.strInstructions

        Glide.with(this)
            .load(meal.strMealThumb)
            .into(findViewById<ImageView>(R.id.ivMealImage))
    }
}