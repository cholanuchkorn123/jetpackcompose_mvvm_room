package com.example.mvvmcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mvvmcompose.compose.BaseScreen
import com.example.mvvmcompose.compose.detial.Detail
import com.example.mvvmcompose.data.ConverterDatabase
import com.example.mvvmcompose.data.ConverterRepoImpl
import com.example.mvvmcompose.ui.theme.MvvmComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val dao = ConverterDatabase.getInstace(application).converterDAO
        val repository = ConverterRepoImpl(dao)
        val factory = ConverterViewModelFactory(repository)
        setContent {
            val navController = rememberNavController()

            MvvmComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "base_screen",
                        builder = {
                            composable("base_screen") {
                                BaseScreen(factory, navController = navController)
                            }

                            composable("details_screen/{msg1}/{msg2}") { backStackEntry ->
                                val msg1 = backStackEntry.arguments?.getString("msg1") ?: "hello"
                                val msg2 = backStackEntry.arguments?.getString("msg2") ?: "world"
                                Detail(msg1 = msg1, msg2 = msg2)
                            }
                        })


                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MvvmComposeTheme {
        Greeting("Android")
    }
}