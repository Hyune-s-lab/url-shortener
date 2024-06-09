package com.hyunec.domain.urlshortener.component

import java.time.Instant

interface PeriodValidationComponent {
    fun endAt(startAt: Instant): Instant
}
