package com.tbt.acabaneyesem.presentation.common.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tbt.acabaneyesem.presentation.ui.theme.DarkGreen

@Composable
fun SearchTextField(
    text: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        text, onValueChange = onValueChange,
        Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .clip(
                RoundedCornerShape(10.dp),
            ),
        textStyle = TextStyle(
            fontWeight = FontWeight.W600,
            fontSize = 20.sp,
            color = DarkGreen
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        placeholder = {
            Text("Kişi Sayısı", color = DarkGreen)
        },
        maxLines = 1,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = DarkGreen.copy(0.3f),
            focusedContainerColor = DarkGreen.copy(0.3f),
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
    )
}