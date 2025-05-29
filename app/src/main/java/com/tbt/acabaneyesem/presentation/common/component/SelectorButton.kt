package com.tbt.acabaneyesem.presentation.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.tbt.acabaneyesem.presentation.ui.theme.DarkGreen

@Composable
fun SelectorButton(text : String,onClick : () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(83.dp)
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(DarkGreen.copy(0.3f))
            .clickable {
                onClick()
            }
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text, color = DarkGreen)
        Box {
            Column {
                Icon(Icons.Default.KeyboardArrowUp, "", modifier = Modifier.size(15.dp))
                Icon(Icons.Default.KeyboardArrowDown, "", modifier = Modifier.size(15.dp))
            }
        }
    }
}