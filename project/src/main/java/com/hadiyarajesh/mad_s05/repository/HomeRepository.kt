package com.hadiyarajesh.mad_s05.repository

import androidx.paging.PagingData
import com.hadiyarajesh.mad_s05.model.Repository
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun searchRepositories(searchQuery: String): Flow<PagingData<Repository>>

    suspend fun getRepository(owner: String, repo: String): Repository
}
