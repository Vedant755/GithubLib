package com.hadiyarajesh.mad_s05.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepositoryResponse(
    @Json(name = "total_count")
    val totalCount: Long,
    val items: List<Repository>
)
