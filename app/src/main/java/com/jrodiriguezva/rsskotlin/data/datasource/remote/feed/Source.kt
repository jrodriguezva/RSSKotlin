package com.jrodiriguezva.rsskotlin.data.datasource.remote.feed

import com.squareup.moshi.Json

data class Source (
    @Json(name = "id")
    private val id: String? = null,
    @Json(name = "name")
    private val name: String? = null)