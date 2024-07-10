package com.hadiyarajesh.mad_s05.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hadiyarajesh.mad_s05.ui.navigation.AppNavigation
import com.hadiyarajesh.mad_s05.ui.navigation.TopLevelDestination
import com.hadiyarajesh.mad_s05.ui.navigation.bottomNavItems
import com.hadiyarajesh.mad_s05.ui.theme.AppTheme

@Composable
fun App() {
    AppTheme {
        val navController = rememberNavController()
        val currentEntry by navController.currentBackStackEntryAsState()

        val shouldShowBottomBar = remember(currentEntry) {
            bottomNavItems.any { it.route == (currentEntry?.destination?.route) }
        }

        Scaffold(
            bottomBar = {
                AnimatedVisibility(
                    visible = shouldShowBottomBar,
                    enter = fadeIn() + expandVertically(),
                    exit = fadeOut() + shrinkVertically()
                ) {
                    BottomBar(
                        destinations = bottomNavItems,
                        onClick = { destination ->
                            navController.navigate(destination.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        currentDestination = navController.currentBackStackEntryAsState().value?.destination
                    )
                }
            }
        ) { innerPadding ->
            AppNavigation(
                modifier = Modifier.padding(innerPadding),
                navController = navController
            )
        }
    }
}

@Composable
private fun BottomBar(
    modifier: Modifier = Modifier,
    destinations: List<TopLevelDestination>,
    onClick: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?
) {
    NavigationBar(modifier = modifier) {
        destinations.forEach { destination ->
            val selected = currentDestination?.hierarchy?.any { navDestination ->
                navDestination.route?.contains(destination.route, true) ?: false
            } ?: false

            NavigationBarItem(
                selected = selected,
                onClick = { onClick(destination) },
                icon = {
                    Icon(
                        imageVector = if (selected) destination.selectedItem else destination.unselectedIcon,
                        contentDescription = destination.route
                    )
                },
                label = { Text(text = destination.title) },
                alwaysShowLabel = true
            )
        }
    }
}
