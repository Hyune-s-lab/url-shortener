package com.hyunec.domain.urlshortener

import java.time.Instant

data class ShortenUrl(
    val originalUrl: String,
    val urlkey: String,
    val validStartAt: Instant,
    val validEndAt: Instant,
)
