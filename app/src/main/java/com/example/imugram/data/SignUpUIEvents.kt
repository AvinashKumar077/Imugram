package com.example.imugram.data

sealed class SignUpUIEvents {

    data class UserNameChanged(val userName: String) : SignUpUIEvents()
    data class EmailChanged(val email: String) : SignUpUIEvents()
    data class DobChanged(val dob: String) : SignUpUIEvents()
    data class PasswordChanged(val password: String) : SignUpUIEvents()
    data class PrivacyPolicyCheckBoxClicked(val status: Boolean) : SignUpUIEvents()

    object SignUpButtonClicked : SignUpUIEvents()

}