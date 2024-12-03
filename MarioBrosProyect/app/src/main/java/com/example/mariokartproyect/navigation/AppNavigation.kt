package com.example.mariokartproyect.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mariokartproyect.MainScreen
import com.example.mariokartproyect.GameScreen
import com.example.mariokartproyect.SharedViewModel
import com.example.mariokartproyect.ui.theme.SplashScreen


//Menu de navegacion que creamos para poder movernos por nuestras diferentes pantalla y tenerlas conectadas
//Se encarga de gestionar las pantallas y la navegacion
@Composable
//Siempre usamos las llamadas a funciones no a clases
//Parametros que te permiten compartir datos entre navegaciones
fun AppNavigation(sharedViewModel: SharedViewModel) {

    //Creamos la variable controlador de simulacion
    val navController = rememberNavController()

    //Creamos nuestro menu ficticio por asi decirlo
    //Componente que gestiona la navegacion por pantallas
    NavHost(
        navController = navController,
        //Empieza por defecto en la pantalla splash
        startDestination = AppScreens.SplashScreen.route
    ) {
        //Dentro de cada pantalla tenemos definidas las funciones que hemos usado como la de controlar y de elegir el modelo del tema
        //Primera pantalla
        composable(AppScreens.SplashScreen.route) {
            //Funcion splah con funcionalidad de navegacion y compartir datos
            SplashScreen(navController,sharedViewModel)
        }
        //Segunda pantalla
        composable(AppScreens.MainScreen.route) {
            MainScreen(navController, sharedViewModel)
        }
        //Tercera pantalla
        composable(AppScreens.GameScreen.route) {
            GameScreen(navController, sharedViewModel.background.value, sharedViewModel)
        }
    }
}