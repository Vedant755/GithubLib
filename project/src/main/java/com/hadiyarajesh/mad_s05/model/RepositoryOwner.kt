package com.hadiyarajesh.mad_s05.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepositoryOwner(
    val id: Long,
    val login: String,
    @Json(name = "html_url")
    val profileUrl: String,
    @Json(name = "avatar_url")
    val profilePicUrl: String
)
