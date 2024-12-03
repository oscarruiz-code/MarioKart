package com.example.mariokartproyect.navigation

//Creamos los objetos de navegacion con los que vamos a trabajar en caso de referenciarlo en un Material
sealed class AppScreens(val route: String){
    //El onjeto que estamos creando es una subclase del objeto AppScreens
    object SplashScreen: AppScreens("splash_screen")
    object MainScreen: AppScreens("main_screen")
    object GameScreen : AppScreens("game_screen")
}