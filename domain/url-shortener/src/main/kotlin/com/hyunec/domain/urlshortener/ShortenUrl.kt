package com.hyunec.domain.urlshortener

import com.hyunec.domain.urlshortener.model.ShortenUrlLevel
import java.time.Instant

data class ShortenUrl(
    val id: Long,
    val level: ShortenUrlLevel,
    val originalUrl: String,
    val urlkey: String,
    val validStartAt: Instant,
    val validEndAt: Instant,
)
