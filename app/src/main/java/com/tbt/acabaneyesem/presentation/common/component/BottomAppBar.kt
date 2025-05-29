package com.tbt.acabaneyesem.presentation.common.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.bottombar.AnimatedBottomBar
import com.example.bottombar.components.BottomBarItem
import com.example.bottombar.model.IndicatorStyle
import com.example.bottombar.model.ItemStyle
import com.example.bottombar.model.VisibleItem
import com.tbt.acabaneyesem.presentation.common.navigation.NavItems
import com.tbt.acabaneyesem.presentation.ui.theme.DarkGreen
import com.tbt.acabaneyesem.util.Constant

@Composable
fun BottomAppBar(navController : NavHostController ){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val navigationItems = listOf(
        NavItems.Home, NavItems.Search, NavItems.Favorites
    )
    val selectedItem by remember(currentRoute) {
        derivedStateOf {
            navigationItems.indexOfFirst { it.route == currentRoute }.coerceAtLeast(0)
        }
    }
    AnimatedBottomBar(
        bottomBarHeight = (Constant.screenWidth()*0.174).dp,
        selectedItem = selectedItem,
        itemSize = navigationItems.take(3).size,
        containerColor = Color.White,
        indicatorStyle = IndicatorStyle.LINE,
        indicatorColor = DarkGreen,
    ) {
        navigationItems.forEachIndexed { index, navigationItem ->
            BottomBarItem(
                selected = currentRoute == navigationItem.route,
                onClick = {
                    if (currentRoute != navigationItem.route) {
                        navController.navigate(navigationItem.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                textColor = DarkGreen,
                iconColor = DarkGreen.copy(0.6f),
                imageVector = navigationItem.icon,
                label = navigationItem.title,
                containerColor = Color.Transparent,
                itemStyle = ItemStyle.STYLE3,
                visibleItem = VisibleItem.BOTH
            )
        }
    }
}