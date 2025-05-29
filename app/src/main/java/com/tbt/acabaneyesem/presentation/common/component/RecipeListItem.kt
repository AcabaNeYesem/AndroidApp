package com.tbt.acabaneyesem.presentation.common.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.tbt.acabaneyesem.R
import com.tbt.acabaneyesem.presentation.ui.theme.DarkGreen
import com.tbt.acabaneyesem.util.Constant

@Composable
fun RecipeListItem(url : String,title : String, servings : String, cookTime : String ,onClick : () -> Unit){
    Row(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                onClick()
            }
    ) {
        AsyncImage(
            model = url,
            contentDescription = null,
            modifier = Modifier
                .width((Constant.screenWidth() * 0.32).dp)
                .height((Constant.screenHeight() * 0.085).dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.placeholder),
            error = painterResource(R.drawable.no_image)
        )

        Column(Modifier.padding(5.dp)) {
            Text(title, fontWeight = FontWeight.Medium)
            Row {
                Text(cookTime, fontWeight = FontWeight.W400, color = DarkGreen)
                Spacer(Modifier.width(5.dp))
                Text("-")
                Spacer(Modifier.width(5.dp))
                Text("$servings Kişi", fontWeight = FontWeight.W400, color = DarkGreen)
            }
        }
    }
}