package com.hadiyarajesh.mad_s05.ui.repo_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadiyarajesh.mad_s05.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryDetailsViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {
    private val _repository =
        MutableStateFlow<RepositoryDetailsUiState>(RepositoryDetailsUiState.Empty)
    val repository: StateFlow<RepositoryDetailsUiState> get() = _repository.asStateFlow()

    fun getRepository(owner: String, repo: String) {
        viewModelScope.launch {
            _repository.value = RepositoryDetailsUiState.Loading
            try {
                val repository = homeRepository.getRepository(owner, repo)
                _repository.value = RepositoryDetailsUiState.Success(repository = repository)
            } catch (e: Exception) {
                _repository.value = RepositoryDetailsUiState.Error(e.message.toString())
            }
        }
    }
}
