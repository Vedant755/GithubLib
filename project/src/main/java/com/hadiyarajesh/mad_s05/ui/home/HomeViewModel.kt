package com.hadiyarajesh.mad_s05.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hadiyarajesh.mad_s05.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {
    val repositories = homeRepository
        .searchRepositories(searchQuery = "kotlin")
        .cachedIn(viewModelScope)
}
