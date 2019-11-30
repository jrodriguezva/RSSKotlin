package com.jrodiriguezva.rsskotlin.domain.model

import java.util.Date

data class Feed(
    val id: Long? = null,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val publishedAt: Date,
    val imageUrl: String
)