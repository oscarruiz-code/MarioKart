package com.example.mariokartproyect

import android.media.MediaPlayer
import android.os.Bundle
import android.provider.MediaStore.Audio.Media
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mariokartproyect.navigation.AppNavigation
import com.example.mariokartproyect.ui.theme.MarioKartProyectTheme
import androidx.activity.viewModels
import com.example.mariokartproyect.ui.theme.SharedViewModelFactory

//Main ejecutador
class MainActivity : ComponentActivity() {
    //Control de usar plantilla del tema que se halla quedado guardada
    //Controla estado de interfaz y logica
    private val sharedViewModel: SharedViewModel by viewModels {
        SharedViewModelFactory(applicationContext)
    }

    //Empezar por la pantalla de naviagacion principal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarioKartProyectTheme {

                //Llama al componente de navegacion
                AppNavigation(sharedViewModel)

            }

        }
    }
}