package com.example.gestionlogin2

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> get() = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> get() = _password

    private val _buttonEnabled = MutableStateFlow(false)
    val buttonEnabled: StateFlow<Boolean> get() = _buttonEnabled

    val isEmailInvalid: Boolean
        get() = _email.value.isNotEmpty() && !isValidEmail(_email.value)

    val isPasswordInvalid: Boolean
        get() = _password.value.length >= 6

    private val validUsers = listOf(
        "tunombre@iesteis.gal" to "123456",
        "tuprimerapellido@iesteis.gal" to "1234567"
    )

    init {
        validateCredentials()
    }

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
        validateCredentials()
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
        validateCredentials()
    }

    private fun validateCredentials() {
        val currentEmail = _email.value
        val currentPassword = _password.value

        val validCredentials = validUsers.any { (email, password) ->
            currentEmail == email && currentPassword == password
        }

        _buttonEnabled.value = validCredentials
    }

    fun login(onLoginSuccess: () -> Unit) {
        if (_buttonEnabled.value) {
            // Realizar acción de inicio de sesión
            onLoginSuccess()
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}".toRegex()
        return email.matches(emailRegex)
    }
}
