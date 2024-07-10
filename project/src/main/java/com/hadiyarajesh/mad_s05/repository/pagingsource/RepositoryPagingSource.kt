package com.hadiyarajesh.mad_s05.repository.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hadiyarajesh.mad_s05.model.Repository
import com.hadiyarajesh.mad_s05.network.GitHubApi

const val FIRST_PAGE = 1

class RepositoryPagingSource(
    private val gitHubApi: GitHubApi,
    private val searchQuery: String = "language:kotlin",
    private val lastUpdated: String,
    private val sortBy: String = "stars",
    private val orderBy: String = "desc",
) : PagingSource<Int, Repository>() {
    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        return try {
            val page = params.key ?: FIRST_PAGE

            val response = gitHubApi.searchRepositories(
                searchQuery = searchQuery,
                lastUpdated = ":>${lastUpdated}",
                sortBy = sortBy,
                orderBy = orderBy,
                page = page,
                pageSize = params.loadSize
            )

            LoadResult.Page(
                data = response.items,
                prevKey = if (page == FIRST_PAGE) null else page - 1,
                nextKey = if (response.items.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
