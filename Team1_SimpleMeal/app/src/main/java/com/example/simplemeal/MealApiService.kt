package com.example.simplemeal

import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {

    @GET("search.php")
    suspend fun searchMeals(@Query("s") query: String): MealResponse

    @GET("random.php")
    suspend fun getRandomMeal(): MealResponse

}