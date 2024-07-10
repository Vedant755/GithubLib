package com.hadiyarajesh.mad_s05.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.hadiyarajesh.mad_s05.ui.home.HomeScreen
import com.hadiyarajesh.mad_s05.ui.home.HomeViewModel
import com.hadiyarajesh.mad_s05.ui.repo_details.RepositoryDetails
import com.hadiyarajesh.mad_s05.ui.search.SearchScreen
import com.hadiyarajesh.mad_s05.ui.settings.SettingsScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = TopLevelDestination.Home.route
    ) {
        composable(route = TopLevelDestination.Home.route) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            val repositories = remember { homeViewModel.repositories }.collectAsLazyPagingItems()

            HomeScreen(
                repositories = repositories,
                onClick = { repository ->
                    navController.navigate(
                        TopLevelDestination.RepositoryDetails.withArgs(
                            repository.owner.login,
                            repository.name
                        )
                    )
                }
            )
        }

        composable(
            route = TopLevelDestination.RepositoryDetails.route + "/{owner}" + "/{repo}",
            arguments = listOf(
                navArgument("owner") {
                    type = NavType.StringType
                },
                navArgument("repo") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val ownerName = backStackEntry.arguments?.getString("owner") ?: return@composable
            val repoName = backStackEntry.arguments?.getString("repo") ?: return@composable

            RepositoryDetails(
                ownerName = ownerName,
                repoName = repoName,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(route = TopLevelDestination.Search.route) { backStackEntry ->
            SearchScreen()
        }

        composable(route = TopLevelDestination.Settings.route) { backStackEntry ->
            SettingsScreen()
        }
    }
}
