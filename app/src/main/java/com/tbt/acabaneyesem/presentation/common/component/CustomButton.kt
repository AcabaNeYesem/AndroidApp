package com.tbt.acabaneyesem.presentation.common.component

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tbt.acabaneyesem.presentation.ui.theme.DarkGreen

@Composable
fun CustomButton (text : String, onClick : () -> Unit){
    Button(
        onClick = {
            onClick()
        },
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.width(390.dp),
        colors = ButtonColors(
            containerColor = DarkGreen,
            contentColor = Color.White,
            disabledContainerColor = DarkGreen,
            disabledContentColor = DarkGreen
        )
    ) {
        Text(text)
    }
}