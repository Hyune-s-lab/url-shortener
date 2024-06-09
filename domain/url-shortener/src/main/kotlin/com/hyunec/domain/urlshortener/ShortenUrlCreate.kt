package com.hyunec.domain.urlshortener

import java.time.Instant

data class ShortenUrlCreate(
    val originalUrl: String,
    val validEndAt: Instant = Instant.now().plusSeconds(60 * 60 * 24),
)
