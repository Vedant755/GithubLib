package com.hadiyarajesh.mad_s05.ui.repo_details

import com.hadiyarajesh.mad_s05.model.Repository

sealed interface RepositoryDetailsUiState {
    data object Empty : RepositoryDetailsUiState
    data object Loading : RepositoryDetailsUiState
    data class Success(val repository: Repository) : RepositoryDetailsUiState
    data class Error(val errorMsg: String) : RepositoryDetailsUiState
}
