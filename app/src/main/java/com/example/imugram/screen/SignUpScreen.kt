package com.example.imugram.screen

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.example.imugram.components.CheckboxComponent
import com.example.imugram.components.ClickableLoginTextComponent
import com.example.imugram.components.DatePicker
import com.example.imugram.components.DividerTextComponent
import com.example.imugram.components.HeadingTextComponent
import com.example.imugram.components.MyTextField
import com.example.imugram.components.NormalTextComponent
import com.example.imugram.components.PasswordTextField
import com.example.imugram.data.SignUpViewModel
import com.example.imugram.data.SignUpUIEvents
import com.example.imugram.navigation.ImugramAppRouter
import com.example.imugram.navigation.Screen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SignUpScreen(SignUpViewModel: SignUpViewModel = viewModel()) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                NormalTextComponent(value = stringResource(id = R.string.hello))
                HeadingTextComponent(value = stringResource(id = R.string.create_account))
                Spacer(modifier = Modifier.height(20.dp))

                MyTextField(
                    labelValue = stringResource(id = R.string.username),
                    painterResource(id = R.drawable.user),
                    onTextSelected = {
                        SignUpViewModel.onEvent(SignUpUIEvents.UserNameChanged(it))
                    },
                    errorStatus = SignUpViewModel.signUpUIState.value.isUsernameError

                )
                MyTextField(
                    labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.mail),
                    onTextSelected = {
                        SignUpViewModel.onEvent(SignUpUIEvents.EmailChanged(it))
                    },
                    errorStatus = SignUpViewModel.signUpUIState.value.isEmailError
                )
                DatePicker(
                    onDateSelected = {
                        SignUpViewModel.onEvent(SignUpUIEvents.DobChanged(it))
                    },
                    errorStatus = SignUpViewModel.signUpUIState.value.isDobError
                )
                PasswordTextField(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.password),
                    onTextSelected = {
                        SignUpViewModel.onEvent(SignUpUIEvents.PasswordChanged(it))
                    },
                    errorStatus = SignUpViewModel.signUpUIState.value.isPasswordError
                )
                CheckboxComponent(
                    value = stringResource(id = R.string.terms_and_conditions),
                    onTextSelected = {
                        ImugramAppRouter.navigate(Screen.TermsAndConditionScreen)
                    },
                    onCheckedChange = {
                        SignUpViewModel.onEvent(SignUpUIEvents.PrivacyPolicyCheckBoxClicked(it))
                    }
                )
                Spacer(modifier = Modifier.height(40.dp))
                ButtonComponent(
                    value = stringResource(id = R.string.register),
                    onButtonClicked = {
                        SignUpViewModel.onEvent(SignUpUIEvents.SignUpButtonClicked)
                    },
                    isEnable = SignUpViewModel.allValidationsPassed.value
                )
                Spacer(modifier = Modifier.height(20.dp))
                DividerTextComponent()
                ClickableLoginTextComponent(
                    tryingToLogin = true,
                    onTextSelected = {
                        ImugramAppRouter.navigate(Screen.LoginScreen)
                    }
                )
            }
        }
        if (SignUpViewModel.signUpInProcess.value){
            CircularProgressIndicator()
        }

    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun DefaultPreviewOfSignUpScreen(){
    SignUpScreen()
}