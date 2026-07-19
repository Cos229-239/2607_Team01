package com.example.simplemeal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simplemeal.ui.theme.BluePrimary
import com.example.simplemeal.ui.theme.SimpleMealTheme
import com.example.simplemeal.ui.theme.SnowDisplay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleMealTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun LoginScreen(name: String, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,


    )
    {
        Text(
            text = "Simple Meal",
            fontSize = 52.sp,
            color = SnowDisplay,
            modifier = modifier.background(BluePrimary)
                .fillMaxWidth().padding(vertical = 40.dp),
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Serif,//till we import our own font
            fontWeight = FontWeight.Bold

        )
        Image(painter = painterResource(id = R.drawable.profile_vector), contentDescription = "Profile Picture")
        Text(
            text = name,
            fontSize = 24.sp,
        )

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleMealTheme {
        LoginScreen("Guest")
    }
}