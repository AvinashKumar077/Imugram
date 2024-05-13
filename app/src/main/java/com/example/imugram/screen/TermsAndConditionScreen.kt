package com.example.imugram.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imugram.R
import com.example.imugram.app.ImugramApp
import com.example.imugram.components.HeadingTextComponent
import com.example.imugram.navigation.ImugramAppRouter
import com.example.imugram.navigation.Screen
import com.example.imugram.navigation.SystemBackButtonHandler

@Composable
fun TermsAndConditionScreen(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            HeadingTextComponent(value = stringResource(id = R.string.terms_and_conditions_header))
            TermsAndConditionsText()
            Button(
                onClick = { ImugramAppRouter.navigate(Screen.SignUpScreen) },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Agree and Continue")
            }
        }
    }
}

@Composable
fun TermsAndConditionsText() {
    Text(
        text = """
            Terms and Conditions
            ---------------------

            Welcome to Imugram!

            Please read these terms and conditions carefully before using the Imugram application.

            By accessing or using the Imugram application, you agree to be bound by these terms and conditions. If you disagree with any part of these terms and conditions, you must not use the Imugram application.

            1. Use of the Application

            1.1. You must be at least 13 years old to use the Imugram application.

            1.2. You agree to use the Imugram application solely for lawful purposes and in accordance with these terms and conditions.

            1.3. You are responsible for maintaining the confidentiality of your account and password and for restricting access to your account. You agree to accept responsibility for all activities that occur under your account or password.

            2. Intellectual Property Rights

            2.1. The Imugram application and its original content, features, and functionality are owned by [Your Company Name] and are protected by international copyright, trademark, patent, trade secret, and other intellectual property or proprietary rights laws.

            2.2. You agree not to reproduce, distribute, modify, create derivative works of, publicly display, publicly perform, republish, download, store, or transmit any of the material on the Imugram application.

            3. Privacy

            3.1. Your use of the Imugram application is subject to our Privacy Policy. Please review our Privacy Policy, which governs the collection, use, and disclosure of your information.

            4. Limitation of Liability

            4.1. In no event shall [Your Company Name], nor its directors, employees, partners, agents, suppliers, or affiliates, be liable for any indirect, incidental, special, consequential, or punitive damages, including without limitation, loss of profits, data, use, goodwill, or other intangible losses, resulting from (i) your access to or use of or inability to access or use the Imugram application; (ii) any conduct or content of any third party on the Imugram application; (iii) any content obtained from the Imugram application; and (iv) unauthorized access, use, or alteration of your transmissions or content, whether based on warranty, contract, tort (including negligence), or any other legal theory, whether or not we have been informed of the possibility of such damage, and even if a remedy set forth herein is found to have failed of its essential purpose.

            5. Governing Law

            5.1. These terms and conditions shall be governed by and construed in accordance with the laws of [Your Country], without regard to its conflict of law provisions.

            6. Changes to Terms and Conditions

            6.1. We reserve the right, at our sole discretion, to modify or replace these terms and conditions at any time. If a revision is material, we will try to provide at least 30 days' notice prior to any new terms taking effect. What constitutes a material change will be determined at our sole discretion.

            By continuing to access or use our Imugram application after those revisions become effective, you agree to be bound by the revised terms. If you do not agree to the new terms, please stop using the Imugram application.

            If you have any questions about these terms and conditions, please contact us at [Your Contact Information].
        """.trimIndent(),
        modifier = Modifier.padding(vertical = 16.dp)
    )
}

@Preview
@Composable
fun TermsPreview(){
    TermsAndConditionScreen()
}
