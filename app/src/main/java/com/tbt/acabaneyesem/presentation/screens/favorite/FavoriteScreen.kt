package com.tbt.acabaneyesem.presentation.screens.favorite

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.tbt.acabaneyesem.domain.model.RecipeDetails
import com.tbt.acabaneyesem.presentation.common.component.BottomAppBar
import com.tbt.acabaneyesem.presentation.common.component.RecipeListItem
import com.tbt.acabaneyesem.presentation.common.component.TopAppBar
import com.tbt.acabaneyesem.presentation.common.navigation.NavItems
import com.tbt.acabaneyesem.presentation.screens.RecipeSharedViewModel
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoritesScreen(
    navController: NavHostController,
    sharedViewModel: RecipeSharedViewModel,
    viewModel: FavoriteViewModel = hiltViewModel(),
) {
    val allSaved = viewModel.allSaved.collectAsState()
    Scaffold(
        containerColor = Color.White,
        modifier = Modifier.systemBarsPadding(),
        topBar = { TopAppBar(navController, "Favorilerim") },
        bottomBar = { BottomAppBar(navController) }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            itemsIndexed(
                items = allSaved.value,
                key = { _, item -> item.id }
            ) { index, item ->
                val deleteAction = SwipeAction(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Sil",
                            tint = Color.White
                        )
                    },
                    background = Color.Red,
                    onSwipe = {
                        viewModel.deleteSavedByID(item.id)
                    }
                )
                SwipeableActionsBox(
                    endActions = listOf(deleteAction)
                ) {
                    RecipeListItem(
                        url = item.imagePath,
                        title = item.title,
                        servings = item.servings,
                        cookTime = item.cookTime
                    ) {
                        sharedViewModel.setRecipe(RecipeDetails(
                            baslik = item.title,
                            hazirlik_süresi = item.prepTime,
                            id = item.recipeID,
                            kac_kisilik = item.servings.toInt(),
                            kategori = item.category,
                            malzemeler = item.ingredients,
                            pisirme_süresi = item.cookTime,
                            url =  item.imagePath,
                            yapilis = item.instruction
                        ))
                        navController.navigate(NavItems.Details.route)
                    }
                }
            }
        }
    }
}
