package com.tbt.acabaneyesem.presentation.common.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tbt.acabaneyesem.presentation.ui.theme.DarkGreen

@Composable
fun RecipeDetailRow(
    title: String,
    value: String
) {
    Column {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                title,
                modifier = Modifier.weight(1f),
                color = DarkGreen.copy(0.8f),
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            )
            Text(value, fontSize = 20.sp)
        }
        Divider(color = DarkGreen.copy(0.4f))
    }
}
