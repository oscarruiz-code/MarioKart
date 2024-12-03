package com.example.mariokartproyect.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

//Tema oscuro de la aplicacion
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF1E88E5),
    secondary = Color(0xFFB0BEC5),
    tertiary = Color(0xFF0D47A1)
)

//Tema claro de la aplicacion
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFFFFFFFF),
    secondary = Color(0xFFFFEB3B),
    tertiary = Color(0xFFFFC107)
)


//Funcion de temas del proyecto
@Composable
fun MarioKartProyectTheme(
    //Carga los temas del proyecto
    darkTheme: Boolean = isSystemInDarkTheme(),
    //Para que sea dinamico si cargar uno u otro
    dynamicColor: Boolean = true,
    //Declaro contenido
    content: @Composable () -> Unit
) {

    //Control de temas de colores de la aplicacion
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            //El contexto lo usamos para accder a configuraciones, recursos, servicios de sistema de nuestro programa
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    //Control de nuestro esquema de caracteristicas
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}