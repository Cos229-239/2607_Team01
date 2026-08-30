package com.example.simplemeal.ui.homescreen


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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simplemeal.MealApiService
import com.example.simplemeal.R
import kotlinx.coroutines.delay
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.time.Duration.Companion.milliseconds
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun HomeScreen(){

    var searchTex by remember {
        mutableStateOf( "")
    }

    val retrofitService = remember {
        Retrofit.Builder()
            .baseUrl("https://themealdb.com")
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
                modifier = Modifier.size(50.dp).clip(CircleShape)

            )



        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = searchTex,
            onValueChange = {searchTex = it},
            modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp, vertical = 10.dp)

        )

        Spacer(modifier = Modifier.height(16.dp))

        FourImageDisplay(apiService = retrofitService,
            onImageClick = {}
        )

    }

}



@Composable
fun FourImageDisplay(apiService: MealApiService, onImageClick: (String)-> Unit){

    var displayUrls by remember {
        mutableStateOf(listOf<String>())
    }


    LaunchedEffect(Unit){

        val mealImageContainer = mutableListOf<String>()

        while(true){

            repeat(4){
                try{
                    val response = apiService.getRandomMeal()
                    val meal = response.meals?.firstOrNull()

                    meal?.strMealThumb?.let{ url ->
                        mealImageContainer.add(url)
                    }
                }catch(e: Exception){

                }
            }

            displayUrls = mealImageContainer
            delay(10000.milliseconds)

        }

    }


    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy((8.dp))
    ) {

        items(displayUrls) { currentUrl ->

            GlideImage(
                imageModel =  {currentUrl},
                modifier = Modifier
                    .aspectRatio(1f)
                    .clickable {

                    }
            )


        }
    }
}





@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen()
}