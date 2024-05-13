package com.example.imugram.data

import android.util.Log
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.imugram.data.rules.Validator
import com.example.imugram.navigation.ImugramAppRouter
import com.example.imugram.navigation.Screen
import com.example.imugram.screen.HomeScreen
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel:ViewModel() {
    private val TAG = LoginViewModel::class.java.simpleName

    var loginUIState =  mutableStateOf(LoginUIState())

    var allValidationPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)

    fun onEvent(event: LoginUIEvents){
        when(event){
            is LoginUIEvents.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(email = event.email)
            }
            is LoginUIEvents.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(password = event.password)
            }
            is LoginUIEvents.LoginButtonClicked -> {
                login()
            }
        }
        validateLoginUIDataWithRules()
    }


    private fun validateLoginUIDataWithRules(){
        val emailResult = Validator.validateEmail(
            eMail = loginUIState.value.email
        )
        val passwordResult = Validator.validatePassword(
            pWord = loginUIState.value.password
        )

        loginUIState.value = loginUIState.value.copy(
            isEmailError = emailResult.status,
            isPasswordError = passwordResult.status
        )

        allValidationPassed.value = emailResult.status && passwordResult.status
    }
    private fun login() {
        loginInProgress.value = true
        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(
                loginUIState.value.email,
                loginUIState.value.password
            )
            .addOnCompleteListener{

                Log.d(TAG, "Inside_Login_Success")
                Log.d(TAG, "login: ${it.isSuccessful}")
                if (it.isSuccessful){
                    loginInProgress.value = false
                    ImugramAppRouter.navigate(Screen.HomeScreen)
                }
            }
            .addOnFailureListener{
                loginInProgress.value = false
                Log.d(TAG, "Inside_Login_Failure")
                Log.d(TAG, "login: ${it.message}")
            }
    }
}