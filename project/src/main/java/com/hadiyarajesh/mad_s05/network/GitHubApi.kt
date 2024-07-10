package com.hadiyarajesh.mad_s05.network

import com.hadiyarajesh.mad_s05.model.Repository
import com.hadiyarajesh.mad_s05.model.RepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {
    // https://api.github.com/search/repositories?q=Kotlin&page=1&per_page=10
    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") searchQuery: String = "Kotlin",
        @Query("page") page: Int = 1,
        @Query("per_page") pageSize: Int = 10,
    ): RepositoryResponse

    // https://api.github.com/search/repositories?q=language:kotlin&pushed:>2023-07-31&sort=stars&order=desc&page=1&per_page=3
    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") searchQuery: String,
        @Query("pushed") lastUpdated: String,
        @Query("sort") sortBy: String = "stars",
        @Query("order") orderBy: String = "desc",
        @Query("page") page: Int = 1,
        @Query("per_page") pageSize: Int = 10,
    ): RepositoryResponse

    // https://api.github.com/repos/square/okhttp
    @GET("repos/{owner}/{repo}")
    suspend fun getRepository(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
    ): Repository
}
