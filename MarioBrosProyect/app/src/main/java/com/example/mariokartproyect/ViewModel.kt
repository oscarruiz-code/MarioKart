package com.example.mariokartproyect

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

//Es la clase encargada de cuando se apague o se pase de pantalla que herede las caracteristicas y las guarde
//Al volver a abrirla se encarga de cargar las caracteristicas que se guardaron antes de apagarla
class SharedViewModel(context: Context) : ViewModel() {
    private val prefs: SharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)

    //Declaramos y inicializamos las variables con el estado cargado
    //Almacena el fondo
    var background = mutableStateOf(prefs.getString("background", "first") ?: "first")
    //Almacena el tema
    var isSecondTheme = mutableStateOf(prefs.getBoolean("isSecondTheme", false))
    //Almacena el color
    var useCreamFont = mutableStateOf(prefs.getBoolean("useCreamFont", true))

    //Funcion para ir guardando las caracteristicas que estamos usando en el momento
    fun saveThemePreferences() {

        prefs.edit().apply {
            //Guarda las caracteristicas
            putString("background", background.value)
            putBoolean("isSecondTheme", isSecondTheme.value)
            putBoolean("useCreamFont", useCreamFont.value)
            //Confirmamos cambios
            apply()
        }
    }
}
