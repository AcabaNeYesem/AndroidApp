package com.tbt.acabaneyesem.presentation.common.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavItems(
    val route: String,
    val icon: ImageVector,
    val title: String
) {
   object Home : NavItems(
       route = "home",
       icon = Icons.Outlined.Home,
       title = "Ana Sayfa"
   )
    object Search : NavItems(
        route = "search",
        icon = Icons.Outlined.Search,
        title = "Arama"
    )
    object Favorites : NavItems(
        route = "favorites",
        icon = Icons.Outlined.FavoriteBorder,
        title = "Favorilerim"
    )
    object Details : NavItems(
        route = "details",
        icon = Icons.Outlined.Check,
        title = "Details"
    )
    object Results : NavItems(
        route = "results",
        icon = Icons.Default.Check,
        title = "Results"
    )
}