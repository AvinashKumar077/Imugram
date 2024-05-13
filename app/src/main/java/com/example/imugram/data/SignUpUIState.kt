package com.example.imugram.data

data class SignUpUIState(
    val username: String = "",
    val email: String = "",
    val dob: String = "",
    val password: String = "",
    val privacyPolicyAccepted: Boolean = false,

    val isUsernameError: Boolean = false,
    val isEmailError: Boolean = false,
    val isDobError: Boolean = false,
    val isPasswordError: Boolean = false,
    val isPrivacyPolicyError: Boolean = false,
)
