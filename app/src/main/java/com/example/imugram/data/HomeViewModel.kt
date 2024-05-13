package com.example.imugram.data

import android.graphics.Color
import android.util.Log
import android.view.WindowInsets
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import java.lang.reflect.Modifier

class HomeViewModel : ViewModel() {
    val isUserLoggedIn :MutableLiveData<Boolean> = MutableLiveData()
    fun checkForActiveSession(){
        if(FirebaseAuth.getInstance().currentUser!=null){
            Log.d("HomeViewModel","User is logged in")
            isUserLoggedIn.value = true
        }else{
            Log.d("HomeViewModel","User is not logged in")
            isUserLoggedIn.value = false
        }
    }
}