package com.example.imugram.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen(){
    object SignUpScreen : Screen()
    object TermsAndConditionScreen : Screen()
    object LoginScreen : Screen()
    object HomeScreen : Screen()

}
object ImugramAppRouter {
    var currentScreen : MutableState<Screen> = mutableStateOf(Screen.SignUpScreen)
    fun navigate(destination: Screen){
        currentScreen.value = destination
    }
}