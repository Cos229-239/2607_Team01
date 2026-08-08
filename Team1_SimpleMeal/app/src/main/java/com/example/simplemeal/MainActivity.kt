package com.example.simplemeal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simplemeal.ui.theme.BluePrimary
import com.example.simplemeal.ui.theme.InterDisplay
import com.example.simplemeal.ui.theme.IntelBody
import com.example.simplemeal.ui.theme.SimpleMealTheme
import com.example.simplemeal.ui.theme.SnowDisplay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleMealTheme {//This is the same as Main in Visual Studio

                LoginScreen(
                    name = "Guest",
                    modifier = Modifier.padding()
                )
            }
        }
    }
}

@Composable //The device I selected was Pixel 10 pro XL (had the most API)
fun LoginScreen(name: String, modifier: Modifier = Modifier) {

    var usrname by remember {
        mutableStateOf("")
    }
    var pswlog by remember {
        mutableStateOf("")
    }
    Column(//column allow you to stack obj vertically (row horizontal) like putting a box on-top a box
        horizontalAlignment = Alignment.CenterHorizontally,


    )

    {

        //Title
        Text(//ctrl + p to see all the parameter goes for any function
            text = "Simple Meal",
            fontSize = 52.sp,
            color = SnowDisplay,
            modifier = modifier.background(BluePrimary)
                .fillMaxWidth().padding(vertical = 40.dp),
            textAlign = TextAlign.Center,
            fontFamily = InterDisplay,//our display font for headers
            fontWeight = FontWeight.Black

        )
        //profile icon
        Image(painter = painterResource(id = R.drawable.profile_vector), contentDescription = "Profile Picture")
        //guest text (will make this invisible, then when user create profile, the name will show.)
        Text(
            text = name,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontFamily = IntelBody,//our body text font
            fontWeight = FontWeight.Normal
        )

        Spacer(modifier=modifier.height(16.dp))//this is how you make space between obj
        //Username Box
        OutlinedTextField(value = usrname, onValueChange = {usrname = it},
            label = { Text(text = "UserName")}
        )
        Spacer(modifier=modifier.height(4.dp))
        //Password box
        OutlinedTextField(value = pswlog, onValueChange = {pswlog = it}, label = {
            Text(
                text = "Password"
            )
        }
        )
        //this is a box, works almost the same as a column or row can be used to make mini section
        //just wanted to show another way of doing something
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                //login Button
                val bwitdth =100.dp// val == value
                Button(onClick = {},colors = ButtonDefaults.buttonColors(
                    containerColor = BluePrimary,

                    )
                ) {
                    Text(
                        text = "Login",
                        textAlign = TextAlign.Center,
                        fontFamily = InterDisplay,
                        fontSize = 24.sp,
                        modifier = modifier.width(bwitdth),
                        color = SnowDisplay
                    )
                }
                Spacer(modifier = modifier.height(4.dp))
                //Sign-Up Button
                Button(onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BluePrimary,

                    )
                ) {
                    Text(
                        text = "Sign up",
                        textAlign = TextAlign.Center,
                        fontFamily = InterDisplay,
                        fontSize = 24.sp,
                        modifier = modifier.width(bwitdth),
                        color = SnowDisplay
                    )
                }


            }


        }

    }

}
//this is a preview mode. in the top right on the same level as MainActivity.kt, there is a button
// three strips next to a phone press that and the preview screen will Pop up.
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimpleMealTheme {
        LoginScreen("Guest")
    }
}