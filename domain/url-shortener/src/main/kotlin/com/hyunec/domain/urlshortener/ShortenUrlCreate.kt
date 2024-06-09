package com.hyunec.domain.urlshortener

import com.hyunec.domain.urlshortener.model.ShortenUrlLevel
import java.time.Instant

data class ShortenUrlCreate(
    val level: ShortenUrlLevel,
    val originalUrl: String,
    val validStartAt: Instant,
    val validEndAt: Instant,
)
