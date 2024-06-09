package com.hyunec.domain.urlshortener.component

import org.springframework.stereotype.Component
import java.time.Instant

@Component
class PeriodValidationComponentAdapter(
    private val validPeriodSecond: Long = DEFAULT_VALID_PERIOD_SECOND,
): PeriodValidationComponent {
    override fun endAt(startAt: Instant): Instant {
        return startAt.plusSeconds(validPeriodSecond)
    }

    companion object {
        const val DEFAULT_VALID_PERIOD_SECOND = 60 * 60 * 24L
    }
}
