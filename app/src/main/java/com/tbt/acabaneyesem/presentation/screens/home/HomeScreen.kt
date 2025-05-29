package com.tbt.acabaneyesem.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.tbt.acabaneyesem.domain.model.RecipeDetails
import com.tbt.acabaneyesem.presentation.common.component.BottomAppBar
import com.tbt.acabaneyesem.presentation.common.component.QuickRecipes
import com.tbt.acabaneyesem.presentation.common.component.Recipe
import com.tbt.acabaneyesem.presentation.common.navigation.NavItems
import com.tbt.acabaneyesem.presentation.screens.RecipeSharedViewModel
import com.tbt.acabaneyesem.presentation.ui.theme.DarkGreen

@Composable
fun HomeScreen(navController: NavHostController,sharedViewModel: RecipeSharedViewModel ,viewModel: HomeViewModel = hiltViewModel()) {
    val quickRecipe = viewModel.quickRecipe.collectAsState()
    val popularRecipe = viewModel.popularRecipe.collectAsState()
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        containerColor = Color.White,
        topBar = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text("AcabaNeYesem", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }
        }, bottomBar = {
            BottomAppBar(navController)
        }) {
        if (quickRecipe.value.isLoading || popularRecipe.value.isLoading) {
            Column(Modifier.padding(it).fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator(color = DarkGreen)
            }
        }else{
            LazyColumn(
                Modifier
                    .padding(it)
                    .padding(15.dp)
                    .background(Color.White)
                    .fillMaxSize()
            ) {
                item {
                    Text("Popüler", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Spacer(Modifier.padding(5.dp))
                    LazyRow {
                        if (popularRecipe.value.error.isNotEmpty()) {
                            item {
                                Text(popularRecipe.value.error, color = Color.Red)
                            }
                        } else if (popularRecipe.value.popularRecipe != null) {
                            items(popularRecipe.value.popularRecipe!!.size) {
                                val recipe = popularRecipe.value.popularRecipe!![it]
                                Recipe(recipe) {
                                    sharedViewModel.setRecipe(
                                        RecipeDetails(
                                            baslik = recipe.baslik,
                                            hazirlik_süresi = recipe.hazirlik_süresi,
                                            id = recipe.id,
                                            kac_kisilik = recipe.kac_kisilik,
                                            kategori = recipe.kategori,
                                            malzemeler = recipe.malzemeler,
                                            pisirme_süresi = recipe.pisirme_süresi,
                                            url = recipe.url,
                                            yapilis = recipe.yapilis
                                        )
                                    )
                                    navController.navigate(NavItems.Details.route)
                                }
                            }
                        }
                    }
                }
                item {
                    Text("Hızlı & Kolay", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Spacer(Modifier.padding(5.dp))
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp),
                        contentPadding = PaddingValues(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        if (quickRecipe.value.error.isNotEmpty()) {
                            item {
                                Text(quickRecipe.value.error, color = Color.Red)
                            }
                        } else if (quickRecipe.value.quickRecipe != null) {
                            items(quickRecipe.value.quickRecipe!!.size) {
                                val recipe = quickRecipe.value.quickRecipe!![it]
                                QuickRecipes(recipe) {
                                    sharedViewModel.setRecipe(
                                        RecipeDetails(
                                            baslik = recipe.baslik,
                                            hazirlik_süresi = recipe.hazirlik_süresi,
                                            id = recipe.id,
                                            kac_kisilik = recipe.kac_kisilik,
                                            kategori = recipe.kategori,
                                            malzemeler = recipe.malzemeler,
                                            pisirme_süresi = recipe.pisirme_süresi,
                                            url = recipe.url,
                                            yapilis = recipe.yapilis
                                        )
                                    )
                                    navController.navigate(NavItems.Details.route)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}