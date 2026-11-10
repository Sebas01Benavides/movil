package com.example.clase5

import com.example.clase5.ui.screens.LoginRoute
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.clase5.ui.theme.Clase5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Clase5Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginRoute(
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}
