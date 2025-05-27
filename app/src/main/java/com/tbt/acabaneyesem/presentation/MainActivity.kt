package com.tbt.acabaneyesem.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import com.tbt.acabaneyesem.presentation.common.navigation.Navigation
import com.tbt.acabaneyesem.presentation.ui.theme.AcabaNeYesemTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AcabaNeYesemTheme {
                Navigation()
            }
        }
    }
}