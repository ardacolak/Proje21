package com.example.projecuma

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val viewModel : AuthViewModel= viewModel()

    NavHost(navController = navController, startDestination = "first") {
        composable("first") { FirstScreen(navController) }

        composable("login") { LoginScreen(navController,viewModel=viewModel) }

        composable("register") { RegisterScreen(navController,viewModel=viewModel) }

        composable("mainScreen") { MyApp(navController,viewModel=viewModel) }

        composable("tespit") { tespitScreen(navController) }
    }
}