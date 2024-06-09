package com.hyunec.domain.urlshortener

import java.time.Instant

data class ShortenUrlCreate(
    val originalUrl: String,
    val validStartAt: Instant,
    val validEndAt: Instant,
)
