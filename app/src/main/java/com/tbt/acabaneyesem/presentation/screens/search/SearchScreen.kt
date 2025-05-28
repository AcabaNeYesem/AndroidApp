package com.tbt.acabaneyesem.presentation.screens.search

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.tbt.acabaneyesem.data.remote.datasource.TarifIstegi
import com.tbt.acabaneyesem.presentation.common.component.BottomAppBar
import com.tbt.acabaneyesem.presentation.common.component.CategorySelectorBottomSheet
import com.tbt.acabaneyesem.presentation.common.component.CustomButton
import com.tbt.acabaneyesem.presentation.common.component.IngredientSelectorBottomSheet
import com.tbt.acabaneyesem.presentation.common.component.SearchTextField
import com.tbt.acabaneyesem.presentation.common.component.SelectorButton
import com.tbt.acabaneyesem.presentation.common.component.TopAppBar
import com.tbt.acabaneyesem.presentation.common.navigation.NavItems
import com.tbt.acabaneyesem.presentation.screens.results.ResultsViewModel
import com.tbt.acabaneyesem.util.Constant
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavHostController,
    resultsViewModel: ResultsViewModel,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val ingredients = viewModel.recipeIngredients.collectAsState()
    var servings by remember { mutableStateOf("") }
    var categoryExpanded by remember { mutableStateOf(false) }
    var ingredientsExpanded by remember { mutableStateOf(false) }
    var category = viewModel.categories.collectAsState()
    var selectedCategories by remember { mutableStateOf(setOf<Int>()) }
    var selectedIngredients by remember { mutableStateOf(setOf<String>()) }
    var searchText by remember { mutableStateOf("") }
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(searchText) {
        delay(300)
        searchQuery = searchText
    }

    Scaffold(
        containerColor = Color.White,
        modifier = Modifier.systemBarsPadding(),
        topBar = { TopAppBar(navController, "Arama") },
        bottomBar = {
            BottomAppBar(navController)
        }) {
        Column(
            Modifier
                .padding(it)
                .fillMaxWidth()
        ) {
            SearchTextField(servings, {
                if (it.all { it.isDigit() }) {
                    servings = it
                }
            })
            SelectorButton("Malzemeler") {
                ingredientsExpanded = true
            }
            SelectorButton("Kategori") {
                categoryExpanded = true
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .height((Constant.screenHeight() * 0.53).dp)
                    .padding(15.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val malzemeler: List<String> = selectedIngredients.toList()
                val kategoriler = selectedCategories.map { index ->
                    category.value[index].name
                }
                CustomButton("Tarif Ara") {
                    if (servings.isEmpty()) {
                        Toast.makeText(
                            context,
                            "Kişi Sayısı Boş Olamaz",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (servings.toIntOrNull() == 0) {
                        Toast.makeText(
                            context,
                            "Kişi Sayısı 0'dan Farklı Bir Değer Olmalı",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (kategoriler.isEmpty()) {
                        Toast.makeText(
                            context,
                            "Kategoriler Boş Olamaz",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (malzemeler.isEmpty()) {
                        Toast.makeText(
                            context,
                            "Malzemeler Boş Olamaz",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        resultsViewModel.setRecipe(
                            TarifIstegi(
                                kategoriler = kategoriler,
                                kisi_sayisi = servings.toInt(),
                                malzemeler = malzemeler
                            )
                        )
                        navController.navigate(NavItems.Results.route)
                    }
                }
            }
            Spacer(Modifier.height(10.dp))
            if (categoryExpanded) {
                CategorySelectorBottomSheet(
                    categoryExpanded = categoryExpanded,
                    onDismissRequest = { categoryExpanded = false },
                    categories = category.value,
                    selectedCategories = selectedCategories,
                    onCategorySelected = { selectedCategories = it }
                )
            }
            if (ingredientsExpanded) {
                IngredientSelectorBottomSheet(
                    ingredientsExpanded = ingredientsExpanded,
                    onDismissRequest = { ingredientsExpanded = false },
                    ingredients = ingredients.value.ingredients ?: emptyList(),
                    selectedIngredients = selectedIngredients,
                    onIngredientsSelected = { selectedIngredients = it },
                    searchText = searchText,
                    onSearchTextChange = { searchText = it }
                )
            }
        }
    }
}