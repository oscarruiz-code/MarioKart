package com.example.mariokartproyect

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.input.key.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavHostController
import com.example.mariokartproyect.ui.theme.Naranja
import kotlinx.coroutines.delay

@Composable
fun GameScreen(navController: NavHostController, background: String,sharedViewModel: SharedViewModel) {

    //Variables a usar en el proyecto
    //Variables remember nos permite manejarlas como queramos en nuestro programa
    //Pero una vez que se cambie de pantalla  o se apague nos aseguramos que se reinicia
    val textColor = if (sharedViewModel.isSecondTheme.value) Color.White else Color.Black
    val currentFontFamily = if (sharedViewModel.useCreamFont.value) FontFamily(Font(R.font.cream)) else FontFamily(Font(R.font.rubik))
    val imagenes = if (sharedViewModel.background.value == "first") R.drawable.fondopantalla else R.drawable.fondopantalla1
    var progresop1 by remember { mutableStateOf(0f) }
    var progresop2 by remember { mutableStateOf(0f) }
    var ganador by remember { mutableStateOf<String?>(null) }
    var inicio by remember { mutableStateOf(false) }
    var temporizador by remember { mutableStateOf(3) }
    var empezar by remember { mutableStateOf<MediaPlayer?>(null) }
    var contexto = LocalContext.current
    var victoria by remember { mutableStateOf<MediaPlayer?>(null) }

    //Funcion de control de la alerta
    if (ganador != null) {

        //Es una funcion que utilizamos para ejecutar un bloque teniendolo controlado
        LaunchedEffect(ganador) {

            victoria = MediaPlayer.create(contexto, R.raw.victoria)
            victoria?.start()

        }

        //Bloque de alerta
        AlertDialog(
            onDismissRequest = { },
            title = { Text("¡Ganador!") },
            text = { Text("$ganador ha ganado!") },

            confirmButton = {
                Button(onClick = { victoria?.stop(); victoria?.release(); navController.navigate("main_screen")
                }) {
                    Text("Volver al Menú")
                }
            }
        )
    }

    //Control de tiempo de espera antes de que comience el juego
    //Control
    LaunchedEffect(Unit) {

        empezar= MediaPlayer.create(contexto,R.raw.empezar)
        empezar?.start()

        while (temporizador > 0) {
            delay(1230)
            temporizador--
        }

        inicio = true
        delay(200)
        empezar?.stop()
        empezar?.release()
    }

    //Cajas
    Box(modifier = Modifier.fillMaxSize().focusable().onKeyEvent { event ->
        //Funcionalidad de teclas de nuestro teclado, es necesario el focusable para que valgan teclas de mi ordenador
        if (inicio) {

            when (event.key) {
                Key.A -> {
                    progresop1 = (progresop1 + 0.01f).coerceAtMost(1f)
                    if (progresop1 >= 1f && ganador == null) ganador = "P1"
                    true
                }
                Key.J -> {
                    progresop2 = (progresop2 + 0.01f).coerceAtMost(1f)
                    if (progresop2 >= 1f && ganador == null) ganador = "P2"
                    true
                }
                else -> false
            }
        } else false
    }) {
        //Imagen
        Image(
            painter = painterResource(id = imagenes),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            //Control de texto del contador
            Text(
                text = if (!inicio) "$temporizador" else "¡Let's Go!",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                fontFamily = currentFontFamily,
                modifier = Modifier.padding(16.dp)
            )
        }


        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                //Barra del jugador 1
                Text(
                    text = "P1",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor,
                    fontFamily = currentFontFamily,
                    modifier = Modifier.padding(end = 16.dp)
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(30.dp)
                        .background(color = Color.Red)
                        .border(width = 2.dp, color = Color.Black)
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(progresop1)
                            .background(color = Naranja)
                    )
                }
            }


            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                //Barra del jugador 2
                Text(
                    text = "P2",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor,
                    fontFamily = currentFontFamily,
                    modifier = Modifier.padding(end = 16.dp)
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(30.dp)
                        .background(color = Color.Blue)
                        .border(width = 2.dp, color = Color.Black)
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(progresop2)
                            .background(color = Color.Cyan)
                    )
                }
            }
        }
    }
}
