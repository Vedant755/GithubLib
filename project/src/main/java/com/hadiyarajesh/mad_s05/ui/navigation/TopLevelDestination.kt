package com.hadiyarajesh.mad_s05.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * This class represents all the main screens in the app.
 */
sealed class TopLevelDestination(
    val title: String,
    val route: String,
    val selectedItem: ImageVector,
    val unselectedIcon: ImageVector,
) {
    data object Home : TopLevelDestination(
        title = "Home",
        route = "home",
        selectedItem = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    )

    data object RepositoryDetails : TopLevelDestination(
        title = "RepositoryDetails",
        route = "repository_details",
        selectedItem = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    )

    data object Search : TopLevelDestination(
        title = "Search",
        route = "search",
        selectedItem = Icons.Filled.Search,
        unselectedIcon = Icons.Outlined.Search
    )

    data object Settings : TopLevelDestination(
        title = "Settings",
        route = "settings",
        selectedItem = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )

    /**
     * This is a helper function to pass arguments to navigation destination.
     * For example, instead of using [TopLevelDestination.Search.route]/first_argument/second_argument
     * you can use like [TopLevelDestination.Detail.withArgs(first_argument, second_argument)]
     */
    fun withArgs(vararg args: Any): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}

val bottomNavItems =
    listOf(TopLevelDestination.Home, TopLevelDestination.Search, TopLevelDestination.Settings)