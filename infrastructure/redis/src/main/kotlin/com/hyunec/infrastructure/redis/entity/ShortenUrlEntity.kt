package com.hyunec.infrastructure.redis.entity

import com.hyunec.domain.urlshortener.model.ShortenUrlLevel
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.time.Instant

@RedisHash(value = "shorten_url", timeToLive = 60 * 60 * 24)
data class ShortenUrlEntity(
    @Id
    val id: Long,
    val level: ShortenUrlLevel,
    val url: String,
    val validStartAt: Instant,
    val validEndAt: Instant,
)
