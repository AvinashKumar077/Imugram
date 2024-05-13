package com.example.imugram.app

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.imugram.data.HomeViewModel
import com.example.imugram.navigation.ImugramAppRouter
import com.example.imugram.navigation.Screen
import com.example.imugram.screen.HomeScreen
import com.example.imugram.screen.LoginScreen
import com.example.imugram.screen.SignUpScreen
import com.example.imugram.screen.TermsAndConditionScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ImugramApp(homeViewModel: HomeViewModel = viewModel()){
    homeViewModel.checkForActiveSession()
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White

    ) {
        if(homeViewModel.isUserLoggedIn.value==true){
            ImugramAppRouter.navigate(Screen.HomeScreen)
        }
        Crossfade(targetState = ImugramAppRouter.currentScreen, label = "") { currentState->
            when(currentState.value){
                is Screen.SignUpScreen->{
                    SignUpScreen()
                }
                is Screen.TermsAndConditionScreen->{
                    TermsAndConditionScreen()
                }
                is Screen.LoginScreen->{
                    LoginScreen()
                }
                is Screen.HomeScreen->{
                    HomeScreen()
                }

            }

        }

    }

}