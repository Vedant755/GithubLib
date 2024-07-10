package com.hadiyarajesh.mad_s05.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hadiyarajesh.mad_s05.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {
    // Regular search
//    private val _repositories = MutableStateFlow<PagingData<Repository>>(PagingData.empty())
//    val repositories: StateFlow<PagingData<Repository>> get() = _repositories
//
//    fun searchRepositories(searchQuery: String) {
//        viewModelScope.launch {
//            homeRepository.searchRepositories(searchQuery = searchQuery).collect { repositories ->
//                _repositories.value = repositories
//            }
//        }
//    }

    // Live search
    private val _searchQuery = MutableStateFlow("")

    fun searchRepositories(searchQuery: String) {
        _searchQuery.value = searchQuery
    }

    val repositories = _searchQuery
        .debounce(500)
        .filter { it.isNotBlank() }
        .flatMapLatest { query ->
            homeRepository
                .searchRepositories(searchQuery = query)
                .cachedIn(viewModelScope)
        }
}
