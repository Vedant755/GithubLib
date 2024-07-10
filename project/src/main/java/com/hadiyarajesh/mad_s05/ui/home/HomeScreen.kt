package com.hadiyarajesh.mad_s05.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.LazyPagingItems
import com.hadiyarajesh.mad_s05.R
import com.hadiyarajesh.mad_s05.model.Repository
import com.hadiyarajesh.mad_s05.ui.components.RepositoriesView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    repositories: LazyPagingItems<Repository>,
    onClick: (Repository) -> Unit
) {
//    val repositories = remember { homeViewModel.repositories }.collectAsLazyPagingItems()
    val uriHandler = LocalUriHandler.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            RepositoriesView(
                modifier = Modifier.fillMaxSize(),
                repositories = repositories,
                onClick = onClick,
                onRepoUrlClick = { repository ->
                    uriHandler.openUri(repository.repoUrl)
                }
            )
        }
    }
}
