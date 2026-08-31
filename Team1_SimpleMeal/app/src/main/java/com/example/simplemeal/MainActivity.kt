package com.example.simplemeal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.simplemeal.ui.NavKeys.route1_0_Login
import com.example.simplemeal.ui.NavKeys.route2_0_DashBoard
import com.example.simplemeal.ui.NavKeys.route3_0_MealPlanScreen
import com.example.simplemeal.ui.NavKeys.route4_0_GroceryList
import com.example.simplemeal.ui.NavKeys.route5_0_Profile
import com.example.simplemeal.ui.homescreen.HomeScreen
import com.example.simplemeal.ui.theme.SimpleMealTheme
import com.example.simplemeal.ui.loginscreen.LoginScreen


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleMealApp()
        }
    }
}


@Composable
fun SimpleMealApp(modifier: Modifier = Modifier){

    val backStack = rememberNavBackStack(route1_0_Login("Guest"))

    SimpleMealTheme {//This is the same as Main in Visual Studio
        Scaffold(modifier = Modifier.fillMaxSize()){innerPadding ->
            Surface(
                modifier = modifier.padding(innerPadding).fillMaxSize()
            ) {
                NavDisplay(
                    backStack = backStack,
                    onBack = {backStack.removeLastOrNull()},
                    entryProvider = {key ->
                        when (key) {

                            is route1_0_Login -> NavEntry(key) {
                                //route3_0_MealPlanScreen(name = key.name)
                                LoginScreen(
                                    onButtonPressed = {
                                    backStack.add(route2_0_DashBoard("Guest"))
                                    },
                                    name = key.name)
                            }
                            is route2_0_DashBoard -> NavEntry(key){
                                HomeScreen(

                                    onProfileButtonPressed = { backStack.add(route5_0_Profile("Guest"))},
                                    onMealImagePressed = { backStack.add(route3_0_MealPlanScreen("Guest"))}


                                )
                            }
                            is route5_0_Profile -> NavEntry(key){
                                ProfileScreen()
                            }
                            is route3_0_MealPlanScreen -> NavEntry(key){
                                MealPlanScreen()
                            }
                            is route4_0_GroceryList -> NavEntry(key){
                                GroceryScreen()
                            }
                            else -> NavEntry(key){}

                        }
                    }
                )
            }
        }
    }
}




//this is a preview mode. in the top right on the same level as MainActivity.kt, there is a button
// three strips next to a phone press that and the preview screen will Pop up.
@Preview(showBackground = true)
@Composable
fun MainPreview() {
    SimpleMealApp()
}