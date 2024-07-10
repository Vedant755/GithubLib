package com.hadiyarajesh.mad_s05.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hadiyarajesh.mad_s05.model.Repository
import com.hadiyarajesh.mad_s05.network.GitHubApi
import com.hadiyarajesh.mad_s05.repository.pagingsource.RepositoryPagingSource
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val gitHubApi: GitHubApi
) : HomeRepository {
    override fun searchRepositories(searchQuery: String): Flow<PagingData<Repository>> {
        val cal = Calendar.getInstance().apply {
            add(Calendar.DATE, -1)
        }
        val yesterday = SimpleDateFormat("yyyy-MM-dd").format(cal.time)

        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 20,
                initialLoadSize = 30
            )
        ) {
            RepositoryPagingSource(
                gitHubApi = gitHubApi,
                searchQuery = searchQuery,
                lastUpdated = yesterday
            )
        }.flow
    }

    override suspend fun getRepository(owner: String, repo: String): Repository {
        return gitHubApi.getRepository(owner = owner, repo = repo)
    }
}
