package com.hadiyarajesh.mad_s05.ui.repo_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hadiyarajesh.mad_s05.R
import com.hadiyarajesh.mad_s05.ui.components.ErrorItem
import com.hadiyarajesh.mad_s05.ui.components.LoadingIndicator
import com.hadiyarajesh.mad_s05.ui.components.RepositoryItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryDetails(
    ownerName: String,
    repoName: String,
    onBackClick: () -> Unit,
    repositoryDetailsViewModel: RepositoryDetailsViewModel = hiltViewModel()
) {
    val uriHandler = LocalUriHandler.current

    LaunchedEffect(Unit) {
        repositoryDetailsViewModel.getRepository(owner = ownerName, repo = repoName)
    }

    val repository by remember { repositoryDetailsViewModel.repository }.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
                ),
                title = { Text(text = stringResource(R.string.repository_details)) })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (repository) {
                is RepositoryDetailsUiState.Loading -> {
                    LoadingIndicator(modifier = Modifier.fillMaxSize())
                }

                is RepositoryDetailsUiState.Success -> {
                    RepositoryItem(
                        modifier = Modifier.padding(8.dp),
                        repository = (repository as RepositoryDetailsUiState.Success).repository,
                        clickable = false,
                        onClick = {},
                        onRepoUrlClick = { repository -> uriHandler.openUri(repository.repoUrl) })
                }

                is RepositoryDetailsUiState.Error -> {
                    ErrorItem(
                        modifier = Modifier.fillMaxSize(),
                        text = (repository as RepositoryDetailsUiState.Error).errorMsg
                    )
                }

                is RepositoryDetailsUiState.Empty -> {

                }
            }
        }
    }
}
