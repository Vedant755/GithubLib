package com.hadiyarajesh.mad_s05.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.hadiyarajesh.mad_s05.ui.components.RepositoriesView
import com.hadiyarajesh.mad_s05.ui.components.SearchBar

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    var searchQuery by remember { mutableStateOf("") }
    val repositories = remember { searchViewModel.repositories }.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = searchQuery,
                onValueChange = { value ->
                    searchQuery = value
                    searchViewModel.searchRepositories(searchQuery = value)
                },
                onClear = { searchQuery = "" }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            Button(onClick = { searchViewModel.searchRepositories(searchQuery = searchQuery) }) {
//                Text(text = "Search")
//            }

            RepositoriesView(
                repositories = repositories,
                onClick = {},
                onRepoUrlClick = {}
            )
        }
    }
}
