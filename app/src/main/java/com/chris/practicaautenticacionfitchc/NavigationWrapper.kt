package com.chris.practicaautenticacionfitchc

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chris.practicaautenticacionfitchc.presentation.home.HomeScreen
import com.chris.practicaautenticacionfitchc.presentation.initial.InitialScreen
import com.chris.practicaautenticacionfitchc.presentation.login.LoginScreen
import com.chris.practicaautenticacionfitchc.presentation.signup.SignUpScreen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun NavigationWrapper(
    navHostController: NavHostController,
    auth: FirebaseAuth
) {

    NavHost(navController = navHostController, startDestination = "home") {
        composable("initial"){
            InitialScreen(
                navigateToLogin = {navHostController.navigate("LogIn")},
                navigateToSignUp = {navHostController.navigate("SignUp")},
            )
        }

        composable("logIn"){
            LoginScreen(auth) { navHostController.navigate("home") }
        }

        composable("SignUp"){
            SignUpScreen(auth, navigateToHome = {})
        }

        composable("home"){
            HomeScreen()
        }
    }
}