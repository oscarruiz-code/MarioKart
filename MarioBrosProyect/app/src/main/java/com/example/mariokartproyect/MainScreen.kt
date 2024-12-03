package com.example.mariokartproyect

import android.media.MediaPlayer
import android.os.Process
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.mariokartproyect.navigation.AppScreens
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay

//Funcionalidad de la pagina principal de la aplicacion
@Composable
fun MainScreen(navController: NavHostController, sharedViewModel: SharedViewModel) {

    val contexto = LocalContext.current
    val mediaPlayer = remember {
        MediaPlayer.create(contexto, R.raw.main).apply {
            isLooping = true
            start()
        }
    }

    //Segun el tema cargar una fuente u otra
    val currentFontFamily = if (sharedViewModel.useCreamFont.value) {
        FontFamily(Font(R.font.cream))
    } else {
        FontFamily(Font(R.font.rubik))
    }

    //Segun el tema cargar una imagen de fondo
    val backgroundImage = if (sharedViewModel.background.value == "first") {
        R.drawable.fondopantalla
    } else {
        R.drawable.fondopantalla1
    }

    //Segun el tema cargar un color de texto
    val textoColor = if (sharedViewModel.isSecondTheme.value) Color.White else Color.Black

    //Caja
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = backgroundImage),
            contentDescription = "Fondo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            //Texto principal
            Text(
                text = "Mario Bros",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = currentFontFamily,
                color = textoColor,
                modifier = Modifier.padding(top = 32.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                //Al darle al boton navega para la pantalla de juego
                Button(onClick = {
                    navController.navigate("game_screen")
                    mediaPlayer.stop()
                    mediaPlayer.release()
                }) {
                    //El texto tendra la fuente que se halla cargado
                    Text(text = "Jugar", fontFamily = currentFontFamily)
                }
                Button(onClick = { salirDeLaApp() }) {
                    Text(text = "Salir", fontFamily = currentFontFamily)
                }
                Button(onClick = {
                    //Cargar todas las caracteristicas del tema seleccionado en la pagina
                    sharedViewModel.background.value = if (sharedViewModel.background.value == "first") "second" else "first"
                    sharedViewModel.isSecondTheme.value = !sharedViewModel.isSecondTheme.value
                    sharedViewModel.useCreamFont.value = !sharedViewModel.useCreamFont.value

                    //Llamamos a la funcion para guardar las caracteristicas que tenemos
                    sharedViewModel.saveThemePreferences()

                }) {
                    Text(text = "Tema", fontFamily = currentFontFamily)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

//Funcion para cerrar la app en el movil
fun salirDeLaApp() {
    Process.killProcess(Process.myPid())
}

//Funcion para previsualizar como va la aplicacion
@Composable
fun MainScreenPreview() {

    val context = LocalContext.current
    val previewViewModel = SharedViewModel(context)
    val navController = rememberNavController()

    MainScreen(navController = navController, sharedViewModel = previewViewModel)
}