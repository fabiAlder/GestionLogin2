package com.example.gestionlogin2

data class LibroState(

    val libreria: List<Libro> = listOf(),
    val estaCargando:Boolean = false
)
