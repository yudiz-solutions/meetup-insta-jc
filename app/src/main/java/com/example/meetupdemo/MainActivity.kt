package com.example.meetupdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.meetupdemo.ui.theme.MeetupDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeetupDemoTheme {
                val context = LocalContext.current
                Surface(color = MaterialTheme.colors.background) {
                    Navigator()
                }
            }
        }
    }
}

@Composable
fun Navigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            InstaLoginPage(onLoginClick = { email, password ->
                navController.navigate("insta_home/$email?password=$password")
            })
        }

        composable(
            "insta_home/{email}?password={password}",
            arguments = listOf(
                navArgument("email") {
                    type = NavType.StringType
                },
                navArgument("password") {
                    type = NavType.StringType
                    defaultValue = "Not Provided"
                }
            )
        ) {
            InstaHomePage()
        }
    }
}