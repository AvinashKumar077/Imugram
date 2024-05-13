package com.example.imugram.data

sealed class LoginUIEvents {

    data class EmailChanged(val email: String) : LoginUIEvents()
    data class PasswordChanged(val password: String) : LoginUIEvents()


    object LoginButtonClicked : LoginUIEvents()

}