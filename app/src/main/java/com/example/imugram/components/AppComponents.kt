package com.example.imugram.components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imugram.R
import com.example.imugram.ui.theme.BgColor
import com.example.imugram.ui.theme.Primary
import com.example.imugram.ui.theme.Secondary
import com.example.imugram.ui.theme.TextColor
import com.example.imugram.ui.theme.componentShapes
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection

@Composable
fun NormalTextComponent(value: String ){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color = TextColor,
        textAlign = TextAlign.Center
    )
}
@Composable
fun HeadingTextComponent(value: String ){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ),
        color = TextColor,
        textAlign = TextAlign.Center
    )
}
@Composable
fun MyTextField(labelValue: String, painterResource: Painter,onTextSelected: (String) -> Unit,errorStatus: Boolean=false){
    val textValue = remember {
        mutableStateOf("")
    }
    Surface(color = BgColor) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(componentShapes.small),
            label = { Text(text = labelValue) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Primary,
                focusedLabelColor = Primary,
                cursorColor = Primary,
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            singleLine = true,
            maxLines = 1,
            value = textValue.value,
            onValueChange = {
                textValue.value = it
                onTextSelected(it)
            },
            leadingIcon = {
                Icon(painter = painterResource, contentDescription ="", modifier = Modifier.size(16.dp) )
            },
            isError = !errorStatus

        )
    }
}
@Composable
fun PasswordTextField(labelValue: String, painterResource: Painter,onTextSelected: (String) -> Unit,errorStatus: Boolean = false){
    val password = remember {
        mutableStateOf("")
    }
    val passwordVisible = remember {
        mutableStateOf(false)
    }
    Surface(color = BgColor) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .clip(componentShapes.small),
            label = { Text(text = labelValue) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Primary,
                focusedLabelColor = Primary,
                cursorColor = Primary,
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
            singleLine = true,
            maxLines = 1,
            value = password.value,
            onValueChange = {
                password.value = it
                onTextSelected(it)
            },
            leadingIcon = {
                Icon(painter = painterResource, contentDescription ="", modifier = Modifier.size(16.dp) )
            },
            trailingIcon = {
                val iconImage = if(passwordVisible.value){
                    Icons.Filled.Visibility
                }else{
                    Icons.Filled.VisibilityOff
                }

                var description = if (passwordVisible.value){
                    stringResource(id = R.string.hide_password)
                }else{
                    stringResource(id = R.string.show_password)
                }
                
                IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                    Icon(imageVector = iconImage, contentDescription = "")
                }
            },
            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            isError = !errorStatus

        )
    }
}
@Composable
fun CheckboxComponent(value: String,onTextSelected : (String) ->Unit,onCheckedChange: (Boolean) -> Unit){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp),
            verticalAlignment = Alignment.CenterVertically
    ) {

        val checkedState = remember {
            mutableStateOf(false)
        }
        Checkbox(checked = checkedState.value ,
            onCheckedChange = {
            checkedState.value = !checkedState.value
            onCheckedChange.invoke(it)
        })

        ClickableTextComponent(value = value,onTextSelected)
    }

}
@Composable
fun ClickableTextComponent(value: String,onTextSelected : (String) ->Unit){
    val initialText = "By continuing you accept our "
    val privacyPolicyText = "Privacy Policy"
    val andText = " and "
    val termsAndConditionsText = "Term of Use"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Primary)){
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        append(andText)
        withStyle(style = SpanStyle(color = Primary)){
            pushStringAnnotation(tag = termsAndConditionsText, annotation = termsAndConditionsText)
            append(termsAndConditionsText)
        }

    }
    ClickableText(text = annotatedString, onClick = { offset->
        annotatedString.getStringAnnotations(offset,offset)
            .firstOrNull()?.also {span->
                Log.d("ClickableTextComponent","{$span}")
                if(span.item == termsAndConditionsText || span.item == privacyPolicyText ){
                    onTextSelected(span.item)
                }
            }
    })

}
@Composable
fun ButtonComponent(value: String,onButtonClicked :() -> Unit,isEnable : Boolean=false){
    Button(onClick = {
         onButtonClicked.invoke()
    },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(50.dp),
        enabled = isEnable

    ) {

        Box(modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                shape = RoundedCornerShape(50.dp)
            ),
            contentAlignment = Alignment.Center
        ){
            Text(text = value,
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold)
        }
    }
}
@Composable
fun MyTextField1(
    labelValue: String,
    painterResource: Painter,
    textValue: String,
    onClick: () -> Unit,
    errorStatus: Boolean
) {
    Surface(color = BgColor) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f)
                    .clip(componentShapes.small),
                label = { Text(text = labelValue) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Primary,
                    focusedLabelColor = Primary,
                    cursorColor = Primary,
                ),
                keyboardOptions = KeyboardOptions.Default,
                value = textValue,
                onValueChange = {

                }, // No-op as the value is updated externally
                readOnly = true, // Make the TextField read-only
                trailingIcon = {
                    Icon(
                        painter = painterResource,
                        contentDescription = "",
                        modifier = Modifier
                            .clickable(onClick = onClick)
                            .size(16.dp)
                    )
                },
                isError = !errorStatus
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePicker(onDateSelected:(String)->Unit,errorStatus: Boolean =false) {
    val calendarState = rememberSheetState()
    val selectedDate = remember { mutableStateOf("") }
    CalendarDialog(
        state = calendarState,

        config = CalendarConfig(
            monthSelection = true,
            yearSelection = true,
        ),
        selection = CalendarSelection.Date { date ->
            selectedDate.value = date.toString() // Convert date to string or format as needed
            Log.d("SelectedDate", "$date")
            onDateSelected(selectedDate.value)
        },

        )

    MyTextField1(
        labelValue = "Pick Your DOB",
        painterResource = painterResource(id = R.drawable.dob), // Replace with your calendar icon
        textValue = selectedDate.value,
        onClick = {
            calendarState.show()
        },
        errorStatus
    )
}


@Composable
fun DividerTextComponent(){
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = Color.Gray,
            thickness = 1.dp
        )
        Text(modifier = Modifier.padding(8.dp),
            text = "or", fontSize = 18.sp, color = TextColor)
        Divider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = Color.Gray,
            thickness = 1.dp
        )

    }
}

@Composable
fun ClickableLoginTextComponent(tryingToLogin: Boolean = true ,onTextSelected : (String) ->Unit){
    val initialText = if (tryingToLogin) "Already have an account?" else "Don't have an account yet?"
    val loginText = if(tryingToLogin) "Login" else "Sign Up"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Primary)){
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }

    }
    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
            style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        text = annotatedString, onClick = { offset->
        annotatedString.getStringAnnotations(offset,offset)
            .firstOrNull()?.also {span->
                Log.d("ClickableTextComponent","{$span}")
                if(span.item == loginText){
                    onTextSelected(span.item)
                }
            }
    })

}
@Composable
fun UnderlinedTextComponent(value: String ){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        color =  colorResource(id = R.color.colorGray),
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline
    )
}
