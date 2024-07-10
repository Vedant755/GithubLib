package com.hadiyarajesh.mad_s05.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.hadiyarajesh.mad_s05.R
import com.hadiyarajesh.mad_s05.model.Repository

@Composable
fun RepositoriesView(
    modifier: Modifier = Modifier,
    repositories: LazyPagingItems<Repository>,
    onClick: (Repository) -> Unit,
    onRepoUrlClick: (Repository) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(repositories) { repository ->
            RepositoryItem(
                modifier = Modifier.fillMaxWidth(),
                repository = repository,
                onClick = onClick,
                onRepoUrlClick = onRepoUrlClick
            )
        }

        repositories.apply {
            when (repositories.loadState.refresh) {
                is LoadState.Loading -> {
                    item {
                        LoadingIndicator(modifier = Modifier.fillParentMaxSize())
                    }
                }

                is LoadState.Error -> {
                    item {
                        ErrorItemWithButton(
                            modifier = Modifier.fillParentMaxSize(),
                            errorMsg = stringResource(R.string.something_went_wrong),
                            onRetryClick = { refresh() }
                        )
                    }
                }

                else -> {}
            }

            when (repositories.loadState.append) {
                is LoadState.Loading -> {
                    item {
                        LoadingIndicator(modifier = Modifier.fillMaxSize())
                    }
                }

                is LoadState.Error -> {
                    item {
                        ErrorItemWithButton(
                            modifier = Modifier.fillMaxSize(),
                            errorMsg = stringResource(R.string.something_went_wrong),
                            onRetryClick = { retry() }
                        )
                    }
                }

                else -> {}
            }
        }
    }
}

@Composable
fun RepositoryItem(
    modifier: Modifier = Modifier,
    clickable: Boolean = true,
    repository: Repository,
    onClick: (Repository) -> Unit,
    onRepoUrlClick: (Repository) -> Unit,
) {
    var isOverflow by remember { mutableStateOf(false) }
    var maxLines by remember { mutableIntStateOf(2) }

    Card(
        modifier = modifier.clickable(
            enabled = clickable,
            onClick = { onClick(repository) }
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .animateContentSize()
        ) {
            Text(
                text = repository.name,
                style = MaterialTheme.typography.titleMedium
            )

            repository.description?.let {
                VerticalSpacer(size = 8)

                Text(
                    text = it,
                    maxLines = maxLines,
                    overflow = TextOverflow.Ellipsis,
                    onTextLayout = { textLayoutResult ->
                        isOverflow = textLayoutResult.hasVisualOverflow
                    }
                )

                if (isOverflow) {
                    Text(
                        modifier = Modifier.clickable { maxLines = Int.MAX_VALUE },
                        text = stringResource(R.string.see_more),
                        color = Color.Gray
                    )
                }
            }
            VerticalSpacer(size = 8)

            Text(
                modifier = Modifier.clickable { onRepoUrlClick(repository) },
                text = repository.repoUrl,
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}
