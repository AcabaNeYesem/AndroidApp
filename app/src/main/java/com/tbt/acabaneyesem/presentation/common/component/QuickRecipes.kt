package com.tbt.acabaneyesem.presentation.common.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import com.tbt.acabaneyesem.data.remote.recipie.quick.dto.RecipeQuickDtoItem
import com.tbt.acabaneyesem.presentation.ui.theme.DarkGreen
import com.tbt.acabaneyesem.util.Constant

@Composable
fun QuickRecipes(
    recipe : RecipeQuickDtoItem,
    onClick: () -> Unit
){
    Column(Modifier.padding(8.dp)
        .height((Constant.screenHeight()*0.32).dp)
        .clip(RoundedCornerShape(12.dp))
        .clickable { onClick() }
    ) {
        AsyncImage(
            model = recipe.url,
            "",
            modifier = Modifier
                .width((Constant.screenWidth()*0.4).dp)
                .height((Constant.screenHeight()*0.17).dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.placeholder),
            error = painterResource(R.drawable.no_image)
        )
        Text(recipe.baslik, fontWeight = FontWeight.Medium)
        Text(recipe.hazirlik_süresi,fontWeight = FontWeight.W400, color = DarkGreen)
    }
}