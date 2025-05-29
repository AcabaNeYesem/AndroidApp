package com.tbt.acabaneyesem.presentation.common.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tbt.acabaneyesem.presentation.screens.RecipeSharedViewModel
import com.tbt.acabaneyesem.presentation.screens.details.DetailsScreen
import com.tbt.acabaneyesem.presentation.screens.favorite.FavoritesScreen
import com.tbt.acabaneyesem.presentation.screens.home.HomeScreen
import com.tbt.acabaneyesem.presentation.screens.results.ResultsScreen
import com.tbt.acabaneyesem.presentation.screens.results.ResultsViewModel
import com.tbt.acabaneyesem.presentation.screens.search.SearchScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val sharedViewModel = hiltViewModel<RecipeSharedViewModel>()
    val resultsViewModel = hiltViewModel<ResultsViewModel>()
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        NavHost(
            navController = navController,
            startDestination = NavItems.Home.route,
        ) {
            composable(route = NavItems.Home.route) {
                HomeScreen(navController, sharedViewModel)
            }
            composable(route = NavItems.Search.route) {
                SearchScreen(navController, resultsViewModel)
            }
            composable(route = NavItems.Favorites.route) {
                FavoritesScreen(navController,sharedViewModel)
            }
            composable(route = NavItems.Details.route) {
                DetailsScreen(navController, sharedViewModel)
            }
            composable(route = NavItems.Results.route) {
                ResultsScreen(navController,sharedViewModel,resultsViewModel)
            }
        }
    }
}