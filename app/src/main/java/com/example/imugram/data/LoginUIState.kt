package com.example.imugram.data

data class LoginUIState(
    val email: String = "",
    val password: String = "",

    val isEmailError: Boolean = false,
    val isPasswordError: Boolean = false,

)
