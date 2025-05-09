package com.tbt.acabaneyesem.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.tbt.acabaneyesem.presentation.ui.theme.AcabaNeYesemTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AcabaNeYesemTheme {

            }
        }
    }
}