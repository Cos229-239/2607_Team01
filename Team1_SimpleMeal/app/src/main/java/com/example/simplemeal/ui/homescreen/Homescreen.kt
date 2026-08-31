package com.example.simplemeal.ui.homescreen


import android.R.attr.onClick
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simplemeal.MealApiService
import com.example.simplemeal.R
import com.example.simplemeal.ui.theme.BluePrimary
import com.example.simplemeal.ui.theme.IntelBody
import com.example.simplemeal.ui.theme.InterDisplay
import kotlinx.coroutines.delay
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.time.Duration.Companion.milliseconds
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun HomeScreen(onProfileButtonPressed: () -> Unit, onMealImagePressed: () ->Unit){

    var searchTex by remember {
        mutableStateOf( "")
    }
    var pathWay by remember { mutableStateOf("") }

    val retrofitService = remember {
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealApiService::class.java)
    }


    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.brand_cream_bg))
            .padding(20.dp),

        ) {
        //top row profile image
        Row{
            Image(
                painter = painterResource(id = R.drawable.profile_vector),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .clickable(
                        onClick = onProfileButtonPressed
                    )


            )

            Spacer(modifier = Modifier.width(20.dp))
            Text(
                fontFamily = InterDisplay,
                text = "Welcome!",
                fontSize = 48.sp,

                color = BluePrimary,
            )



        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = searchTex,
            onValueChange = {searchTex = it},
            modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp, vertical = 10.dp)

        )

        Spacer(modifier = Modifier.height(40.dp))

        FourImageDisplay(
            apiService = retrofitService,
            onImageClick = onMealImagePressed

        )

    }

}



@Composable
fun FourImageDisplay(apiService: MealApiService, onImageClick: ()-> Unit){

    var displayUrls by remember {
        mutableStateOf(listOf<Pair<String?,String?>>())
    }


    LaunchedEffect(Unit){

        val mealImageContainer = mutableListOf<Pair<String?,String?>>()

        while(true){

            repeat(4){

                val response = apiService.getRandomMeal()
                val meal = response.meals?.firstOrNull()

                val url = meal?.strMealThumb
                val title = meal?.strMeal

                mealImageContainer.add(Pair(url,title))
            }

            displayUrls = mealImageContainer
            delay(5000.milliseconds)

        }

    }


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy((20.dp))
    ) {

        items(displayUrls) { currentUrl ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ){
            GlideImage(
                imageModel =  {currentUrl.first},
                modifier = Modifier
                    .aspectRatio(1f)
                    .clickable (
                        onClick = onImageClick
                    )
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = currentUrl.second.toString(),
                fontSize = 10.sp,
                fontFamily = IntelBody,
            )
            }

        }
    }
}





@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen(onProfileButtonPressed = {}, onMealImagePressed = {})
}