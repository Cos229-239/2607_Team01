package com.example.simplemeal.ui.NavKeys

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data class route1_0_Login(var name: String) : NavKey


//data object route1_1_Signin

//data object route1_3_ContinueAsGuest
@Serializable
data class route2_0_DashBoard(var name: String): NavKey
@Serializable
data class route3_0_MealPlanScreen(var name: String) : NavKey{
}

//data object route4_0_GroceryList
@Serializable
data class route5_0_Profile(var name: String) : NavKey