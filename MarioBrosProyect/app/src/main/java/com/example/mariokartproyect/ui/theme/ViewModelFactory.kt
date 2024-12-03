package com.example.mariokartproyect.ui.theme

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mariokartproyect.SharedViewModel

//Funcion para crear la instancia, que se utiliza para asegurarnos de que se lo pasa correcto

class SharedViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //Creamos el modelo vista
        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            return SharedViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}