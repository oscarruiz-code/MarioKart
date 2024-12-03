package com.example.mariokartproyect.ui.theme

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mariokartproyect.R
import com.example.mariokartproyect.SharedViewModel
import com.example.mariokartproyect.navigation.AppScreens
import kotlinx.coroutines.delay

//Es la pantalla que utilizamos antes de cargar la aplicacion del setcontent para darle funcionalidad
@Composable
//Permiso para navegar y compartir datos entre pantallas
fun SplashScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    val progreso = remember { mutableStateOf(0f) }
    val contexto = LocalContext.current

    // Control del MediaPlayer
    //Key1 = true, significa que se crea solo cuando la pantalla se carga
    LaunchedEffect(key1 = true) {

        var fondo = MediaPlayer.create(contexto,R.raw.fondo)
        fondo.start()

        // Actualizar la barra de progreso
        for (i in 1..100) {
            delay(50)
            progreso.value = i / 100f
        }

        // Navegar a la siguiente pantalla después de un breve retraso
        delay(500)
        //Nos aseguramos de cerrar pantalla de navegacion
        navController.popBackStack()
        //Navegamos a la siguiente
        navController.navigate("main_screen")
        fondo.stop()
        fondo.release()
    }

    // Color de progreso según el tema
    val progressColor = if (sharedViewModel.isSecondTheme.value) {
        Color.Red
    } else {
        Color.Yellow
    }

    // Llamada al contenido de la funcion visual del splash
    Splash(sharedViewModel, progreso.value, progressColor)
}

//Es la pantalla que tiene el contenido del splash
@Composable
//Pasamos parametros para avilitarlos en nuestra funcion
fun Splash(sharedViewModel: SharedViewModel, progreso: Float, proguesocolor: Color) {

    //Cargamos el fonde segun el tema que se halla quedado por cargar
    val backgroundImage = if (sharedViewModel.background.value == "first") {
        R.drawable.carga
    } else {
        R.drawable.carga1
    }

    //Caja de la interfaz
    Box(modifier = Modifier.fillMaxSize()) {
        //Imagen
        Image(
            painter = painterResource(id = backgroundImage),
            contentDescription = "Fondo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Spacer(modifier = Modifier.weight(1f))

            //Barra de progreso
            LinearProgressIndicator(
                progress = progreso,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp),
                color = proguesocolor,
                trackColor = androidx.compose.ui.graphics.Color.White
            )
        }
    }
}

//Solo lo utilizamos para ir controlando el preview del splash
@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {

    val context = LocalContext.current
    val previewViewModel = SharedViewModel(context)
    SplashScreen(navController = rememberNavController(), sharedViewModel = previewViewModel)

}