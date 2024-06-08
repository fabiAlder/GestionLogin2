package com.example.gestionlogin2

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _username = MutableStateFlow("")
    val username: StateFlow<String> get() = _username

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> get() = _password

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> get() = _errorMessage

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> get() = _isLoggedIn

    private val validUsers = mutableListOf("tunombre@iesteis.gal" to "123456", "tuprimerapellido@iesteis.gal" to "1234567")

    fun onUsernameChange(newUsername: String) {
        _username.value = newUsername
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun login() {
        viewModelScope.launch {
            if (validUsers.contains(_username.value to _password.value)) {
                _errorMessage.value = ""
                _isLoggedIn.value = true
            } else {
                _errorMessage.value = "Email o contraseña inválidos"
            }
        }
    }

    fun register(email: String, password: String, nombre: String) {
        viewModelScope.launch {
            if (email.isValidEmail() && password.isValidPassword() && nombre.isValidNombre()) {
                validUsers.add(email to password)
                _isLoggedIn.value = true
            } else {
                _errorMessage.value = "Datos de registro inválidos"
            }
        }
    }
}

private const val MIN_SIZE_NAME = 3
private const val MIN_SIZE_PASSWORD = 3

fun String.isValidNombre() = this.length > MIN_SIZE_NAME
fun String.isValidPassword() = this.length > MIN_SIZE_PASSWORD
fun String.isValidEmail() = Patterns.EMAIL_ADDRESS.matcher(this).matches()
