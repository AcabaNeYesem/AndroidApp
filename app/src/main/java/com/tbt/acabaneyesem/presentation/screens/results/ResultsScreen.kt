package com.tbt.acabaneyesem.presentation.screens.results

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.tbt.acabaneyesem.domain.model.RecipeDetails
import com.tbt.acabaneyesem.presentation.common.component.RecipeListItem
import com.tbt.acabaneyesem.presentation.common.component.TopAppBar
import com.tbt.acabaneyesem.presentation.common.navigation.NavItems
import com.tbt.acabaneyesem.presentation.screens.RecipeSharedViewModel
import com.tbt.acabaneyesem.presentation.ui.theme.DarkGreen

@Composable
fun ResultsScreen(
    navController: NavHostController,
    sharedViewModel: RecipeSharedViewModel,
    resultsViewModel: ResultsViewModel
) {

    LaunchedEffect(Unit) {
        resultsViewModel.getSearchRecipe()
    }

    val recipesList = resultsViewModel.searchRecipes.collectAsState()
    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = { TopAppBar(navController, "Arama") },
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            if (recipesList.value.isLoading) {
                item {
                    Column(
                        Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(color = DarkGreen)
                    }
                }
            } else if (recipesList.value.error.isNotEmpty()) {
                item {
                    Column(
                        Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(recipesList.value.error.toString(), color = Color.Red)
                    }
                }
            } else {
                if (recipesList.value.searchRecipe != null) {
                    val recipes = recipesList.value.searchRecipe!!
                    items(recipes.size) { index ->
                        RecipeListItem(
                            url = recipes[index].url,
                            title = recipes[index].baslik,
                            servings = recipes[index].kac_kisilik.toString(),
                            cookTime = recipes[index].pisirme_süresi
                        ) {
                            sharedViewModel.setRecipe(
                                RecipeDetails(
                                    baslik = recipes[index].baslik,
                                    hazirlik_süresi = recipes[index].hazirlik_süresi,
                                    id = recipes[index].id,
                                    kac_kisilik = recipes[index].kac_kisilik,
                                    kategori = recipes[index].kategori,
                                    malzemeler = recipes[index].malzemeler,
                                    pisirme_süresi = recipes[index].pisirme_süresi,
                                    url = recipes[index].url,
                                    yapilis = recipes[index].yapilis
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