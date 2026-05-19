package com.chris.practicaautenticacionfitchc

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chris.practicaautenticacionfitchc.presentation.initial.InitialScreen
import com.chris.practicaautenticacionfitchc.presentation.login.LoginScreen
import com.chris.practicaautenticacionfitchc.presentation.signup.SignUpScreen

@Composable
fun NavigationWrapper(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = "initial") {
        composable("initial"){
            InitialScreen()
        }
        composable("logIn"){
            LoginScreen()
        }
        composable("SignUp"){
            SignUpScreen()
        }
    }
}