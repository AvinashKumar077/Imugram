package com.example.imugram.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.imugram.R
import com.example.imugram.components.ButtonComponent
import com.example.imugram.components.ClickableLoginTextComponent
import com.example.imugram.components.DividerTextComponent
import com.example.imugram.components.HeadingTextComponent
import com.example.imugram.components.MyTextField
import com.example.imugram.components.NormalTextComponent
import com.example.imugram.components.PasswordTextField
import com.example.imugram.components.UnderlinedTextComponent
import com.example.imugram.data.LoginUIEvents
import com.example.imugram.data.LoginViewModel
import com.example.imugram.data.SignUpViewModel
import com.example.imugram.data.SignUpUIEvents
import com.example.imugram.navigation.ImugramAppRouter
import com.example.imugram.navigation.Screen
import com.example.imugram.navigation.SystemBackButtonHandler

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel()) {
    // Implement your login screen logic here
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Surface(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)) {
            Column(modifier = Modifier
                .fillMaxSize()) {
                NormalTextComponent(value = stringResource(id = R.string.login))
                HeadingTextComponent(value = stringResource(id = R.string.welcome))
                Spacer(modifier = Modifier.height(20.dp))
                MyTextField(labelValue = stringResource(id = R.string.email), painterResource = painterResource(id = R.drawable.mail), onTextSelected = {
                    loginViewModel.onEvent(LoginUIEvents.EmailChanged(it))
                }, errorStatus = loginViewModel.loginUIState.value.isEmailError)
                PasswordTextField(labelValue = stringResource(id = R.string.password), painterResource = painterResource(
                    id = R.drawable.password), onTextSelected = {
                    loginViewModel.onEvent(LoginUIEvents.PasswordChanged(it))
                },errorStatus = loginViewModel.loginUIState.value.isPasswordError)
                Spacer(modifier = Modifier.height(40.dp))
                UnderlinedTextComponent(value = stringResource(id = R.string.forgot_password))
                Spacer(modifier = Modifier.height(40.dp))
                ButtonComponent(value = stringResource(id = R.string.login), onButtonClicked = {
                    loginViewModel.onEvent(LoginUIEvents.LoginButtonClicked)
                },
                    isEnable = loginViewModel.allValidationPassed.value)
                Spacer(modifier = Modifier.height(20.dp))
                DividerTextComponent()
                ClickableLoginTextComponent(tryingToLogin = false,onTextSelected ={
                    ImugramAppRouter.navigate(Screen.SignUpScreen)
                })
            }
        }
        if(loginViewModel.loginInProgress.value){
        CircularProgressIndicator()
        }
    }

    SystemBackButtonHandler {
        ImugramAppRouter.navigate(Screen.SignUpScreen)
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}

