package com.tbt.acabaneyesem.presentation.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.tbt.acabaneyesem.R
import com.tbt.acabaneyesem.data.local.saved.entity.Saved
import com.tbt.acabaneyesem.presentation.common.component.FeedbackBottomSheet
import com.tbt.acabaneyesem.presentation.common.component.RecipeDetailRow
import com.tbt.acabaneyesem.presentation.common.component.TopAppBar
import com.tbt.acabaneyesem.presentation.screens.RecipeSharedViewModel
import com.tbt.acabaneyesem.presentation.ui.theme.DarkGreen
import com.tbt.acabaneyesem.util.Constant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    navController: NavHostController,
    sharedViewModel: RecipeSharedViewModel,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val feedS = viewModel.feedbackState.collectAsState()
    val selectedRecipe = sharedViewModel.selectedRecipe.value
    var showFeedbackSheet by remember { mutableStateOf(false) }
    val feedBackList = listOf(
        feedBack(1, "Görsel Yüklenmiyor"),
        feedBack(2, "Görsel Yok"),
        feedBack(3, "Görsel Alakasız"),
        feedBack(4, "Tarif Eksik"),
        feedBack(5, "Yapılış Karmaşık"),
        feedBack(6, "Yanlış Bilgi"),
        feedBack(7, "Tarif Açılmıyor"),
        feedBack(8, "Yavaş Çalışıyor"),
    )
    var selectedCategory by remember { mutableStateOf<Int?>(null) }
    if (selectedRecipe != null) {
        val recipe = selectedRecipe
        viewModel.checkIfSaved(recipe.id)
        val isSaved = viewModel.isSaved.collectAsState()
        Scaffold(
            containerColor = Color.White,
            modifier = Modifier.systemBarsPadding(),
            topBar = { TopAppBar(navController, recipe.baslik) },
        ) {
            LazyColumn(
                Modifier
                    .padding(it)
                    .fillMaxWidth()
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height((Constant.screenHeight() * 0.3).dp),
                    ) {
                        AsyncImage(
                            model = recipe.url,
                            contentDescription = "image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize(),
                            placeholder = painterResource(id = R.drawable.placeholder),
                            error = painterResource(R.drawable.no_image)
                        )
                        Column(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(5.dp)
                        ) {
                            IconButton(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .size(40.dp)
                                    .background(Color.White),
                                onClick = {
                                    if (isSaved.value) {
                                        viewModel.getSavedIDByRecipeID(recipe.id) { id ->
                                            if (id != null)
                                                viewModel.deleteSavedByID(id, recipe.id)
                                        }
                                    } else {
                                        viewModel.insertSaved(
                                            Saved(
                                                recipeID = recipe.id,
                                                title = recipe.baslik,
                                                servings = recipe.kac_kisilik.toString(),
                                                cookTime = recipe.pisirme_süresi,
                                                prepTime = recipe.hazirlik_süresi,
                                                ingredients = recipe.malzemeler,
                                                instruction = recipe.yapilis,
                                                category = recipe.kategori,
                                                imagePath = "",
                                            ), imageUrl = recipe.url
                                        )
                                    }
                                }
                            ) {
                                Icon(
                                    if (isSaved.value) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                    "",
                                    tint = Color.Black
                                )
                            }
                            Spacer(Modifier.height(5.dp))
                            IconButton(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .size(40.dp)
                                    .background(Color.White),
                                onClick = { showFeedbackSheet = true }
                            ) {
                                Icon(Icons.Outlined.Warning, "", tint = Color.Red)
                            }
                        }
                    }
                }
                item {
                    Spacer(Modifier.padding(1.dp))
                    Row(Modifier.padding(5.dp)) {
                        Text("Malzemeler", fontWeight = FontWeight.Medium, fontSize = 20.sp)
                    }
                    Spacer(Modifier.padding(1.dp))
                }
                items(recipe.malzemeler.size) {
                    Row(
                        modifier = Modifier.padding(horizontal = 5.dp),
                    ) {
                        Text("●", color = DarkGreen)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = recipe.malzemeler[it])
                    }
                }
                item {
                    Spacer(Modifier.padding(3.dp))
                    Row(Modifier.padding(5.dp)) {
                        Text("Yapılış", fontWeight = FontWeight.Medium, fontSize = 20.sp)
                    }
                    Spacer(Modifier.padding(3.dp))
                }
                items(recipe.yapilis.size) {
                    Row(
                        modifier = Modifier.padding(horizontal = 5.dp),
                    ) {
                        Text("●", color = DarkGreen)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = recipe.yapilis[it])
                    }
                }
                item {
                    Spacer(Modifier.padding(3.dp))
                    Column(Modifier.padding(5.dp)) {
                        Row {
                            Text("Detaylar", fontWeight = FontWeight.Medium, fontSize = 20.sp)
                        }
                        Spacer(Modifier.padding(3.dp))
                        Divider(color = DarkGreen.copy(0.4f))
                        RecipeDetailRow("Hazırlanış Süresi", recipe.hazirlik_süresi)
                        RecipeDetailRow("Pişirme Süresi", recipe.pisirme_süresi)
                        RecipeDetailRow("Kaç Kişilik", recipe.kac_kisilik.toString())
                        RecipeDetailRow("Kategori", recipe.kategori)
                    }
                }
            }
            if (showFeedbackSheet) {
                FeedbackBottomSheet(
                    feedBackList = feedBackList,
                    selectedCategory = selectedCategory,
                    onSelectCategory = { selectedCategory = it },
                    onSendFeedback = { feedback -> viewModel.sendFeedBack(feedback) },
                    isLoading = feedS.value.isLoading,
                    error = feedS.value.error,
                    recipeId = recipe.id.toString(),
                    onDismiss = { showFeedbackSheet = false }
                )
            }
        }
    }
}

data class feedBack(
    val id: Int,
    val message: String
)