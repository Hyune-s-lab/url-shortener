package com.hyunec.domain.urlshortener.component

import com.hyunec.domain.urlshortener.model.ShortenUrlLevel
import java.time.Instant

interface PeriodValidationComponent {
    fun endAt(shortenUrlLevel: ShortenUrlLevel,  startAt: Instant): Instant
}
