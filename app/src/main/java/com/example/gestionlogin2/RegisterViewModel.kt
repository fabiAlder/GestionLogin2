package com.example.gestionlogin2



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> get() = _errorMessage

    private val registeredUsers = mutableListOf<Pair<String, String>>()

    fun setErrorMessage(errorMessage: String) {
        _errorMessage.value = errorMessage
    }

    fun register(email: String, password: String, nombre: String) {
        viewModelScope.launch {
            if (registeredUsers.any { it.first == email }) {
                _errorMessage.value = "Email ya registrado"
            } else {
                registeredUsers.add(email to password)
                _errorMessage.value = "Usuario registrado con éxito"
            }
        }
    }
}
