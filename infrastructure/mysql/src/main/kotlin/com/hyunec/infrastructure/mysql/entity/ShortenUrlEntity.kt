package com.hyunec.infrastructure.mysql.entity

import com.hyunec.domain.urlshortener.model.ShortenUrlLevel
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant

@Table(name = "shorten_url")
@Entity
class ShortenUrlEntity(
    @Enumerated(EnumType.STRING)
    val level: ShortenUrlLevel,
    val url: String,
    val validStartAt: Instant,
    val validEndAt: Instant,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
