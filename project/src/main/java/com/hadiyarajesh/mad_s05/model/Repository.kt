package com.hadiyarajesh.mad_s05.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Repository(
    val id: Long,
    val name: String,
    @Json(name = "html_url")
    val repoUrl: String,
    val description: String?,
    val owner: RepositoryOwner
)
