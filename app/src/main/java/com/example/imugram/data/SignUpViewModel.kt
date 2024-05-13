package com.example.imugram.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.imugram.data.rules.Validator
import com.example.imugram.navigation.ImugramAppRouter
import com.example.imugram.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

class SignUpViewModel : ViewModel() {
    private val TAG = SignUpViewModel::class.java.simpleName
    var signUpUIState = mutableStateOf(SignUpUIState())

    var allValidationsPassed = mutableStateOf(false)

    var signUpInProcess = mutableStateOf(false)

    fun onEvent(event: SignUpUIEvents) {

        when (event) {
                is SignUpUIEvents.UserNameChanged -> {
                    signUpUIState.value = signUpUIState.value.copy(username = event.userName)
                    printState()
                }
                is SignUpUIEvents.EmailChanged -> {
                    signUpUIState.value = signUpUIState.value.copy(email = event.email)
                    printState()
                }
                is SignUpUIEvents.DobChanged ->{
                    signUpUIState.value = signUpUIState.value.copy(dob = event.dob)
                    printState()
                }
                is SignUpUIEvents.PasswordChanged-> {
                    signUpUIState.value = signUpUIState.value.copy(password = event.password)
                    printState()
                }
                is SignUpUIEvents.SignUpButtonClicked -> {
                   signUp()
                }
                is SignUpUIEvents.PrivacyPolicyCheckBoxClicked->{
                    signUpUIState.value = signUpUIState.value.copy(
                        privacyPolicyAccepted = event.status
                    )
                }
        }
        validateDataWithRules()
    }

    private fun signUp() {
        Log.d(TAG, "Inside_signUp")
        printState()
        createUserInFirebase(
            email = signUpUIState.value.email,
            password = signUpUIState.value.password
        )
    }

    private fun validateDataWithRules() {
        val uNameResult = Validator.validateUserName(
            uName = signUpUIState.value.username
        )
        val emailResult = Validator.validateEmail(
            eMail = signUpUIState.value.email
        )
        val dobResult = Validator.validateDOB(
            dOB = signUpUIState.value.dob
        )
        val passwordResult = Validator.validatePassword(
            pWord = signUpUIState.value.password
        )
        val privacyPolicyResult = Validator.validatePrivacyPolicyAcceptance(
            statusValue = signUpUIState.value.privacyPolicyAccepted
        )

        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "uNameResult $uNameResult")
        Log.d(TAG, "emailResult $emailResult")
        Log.d(TAG, "dobResult $dobResult")
        Log.d(TAG, "passwordResult $passwordResult")
        Log.d(TAG, "privacyPolicyResult $privacyPolicyResult")

        signUpUIState.value = signUpUIState.value.copy(
            isUsernameError = uNameResult.status,
            isEmailError = emailResult.status,
            isDobError = dobResult.status,
            isPasswordError = passwordResult.status,
            isPrivacyPolicyError = privacyPolicyResult.status
        )

        if (uNameResult.status && emailResult.status && dobResult.status && passwordResult.status) {
            if (privacyPolicyResult.status){
                allValidationsPassed.value = true
            }else{
                allValidationsPassed.value = false
            }
        }else{
            allValidationsPassed.value = false
        }
    }

    private fun printState() {
        Log.d(TAG, "Inside_printSate")
        Log.d(TAG, "printState: ${signUpUIState.value}")
    }

    private fun createUserInFirebase(email: String, password: String) {
        signUpInProcess.value = true
        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "Inside_On_Success_Listener")
                if (it.isSuccessful) {
                    Log.d(TAG, "createUserInFirebase: Success")
                } else {
                    Log.d(TAG, "createUserInFirebase: ${it.exception}")
                }
                signUpInProcess.value = false
                if (it.isSuccessful){
                    ImugramAppRouter.navigate(Screen.HomeScreen)
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "Inside_On_Failure_Listener")
                Log.d(TAG, "createUserInFirebase: ${it.message}")
            }


    }

    fun logOut(){
        val firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signOut()
        val authStateListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                Log.d(TAG, "logOut success")
                ImugramAppRouter.navigate(Screen.LoginScreen)
            }else{
                Log.d(TAG, "logOut failed")
            }
        }
        firebaseAuth.addAuthStateListener(authStateListener)
    }
}