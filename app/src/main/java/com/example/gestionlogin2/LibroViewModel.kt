package com.example.gestionlogin2

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LibroViewModel: ViewModel() {
    //var libreria by mutableStateOf(listOf<Libro>())
    //combio con el uso de State
    var state by mutableStateOf(LibroState())
        private set//para sólo cambiar desde aquí y no poder acceder desde fuera

    //var loading by mutableStateOf() //pero la mejor opción será tener un data class pej. LibroState

    init {//actualización del listado de libros después de 5s
        viewModelScope.launch {
            state = state.copy(//Ultimo.2
                estaCargando = true
            )
            delay(5000)//una corrutina
            //OPCION Sin LibroState
            // libreria= listOf(

            //OPCION Con LibroState
//            state.libreria.addAll(//Mejora de LibroState..uso de copy
//                mutableListOf(
//                    Libro("Clean Code","Robert C Martin"),
//                    Libro("Refactoring","Martin Fowler"),
//                    Libro("Effective Java","Joshua Bloch")
//                )
//            )

            state=state.copy(//Ultimo.1-para no cambiar el estado desde aquí
                libreria= listOf(
                    Libro("Clean Code","Robert C Martin"),
                    Libro("Refactoring","Martin Fowler"),
                    Libro("Effective Java","Joshua Bloch")
                ),
                estaCargando = false//para que no se siga mostrando la animación de carga
            )
        }
    }

    fun libroCliked(libro:Libro){
//        Log.i("LibroVM","Cliked:${libro.titulo}")
        val index = state.libreria.indexOf(libro)

        val libreriaModificada = state.libreria.toMutableList()
        libreriaModificada.removeAt(index)

        state=state.copy(
            libreria=libreriaModificada
        )
    }
}