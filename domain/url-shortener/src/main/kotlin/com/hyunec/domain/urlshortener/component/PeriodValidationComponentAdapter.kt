package com.hyunec.domain.urlshortener.component

import com.hyunec.domain.urlshortener.model.ShortenUrlLevel
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class PeriodValidationComponentAdapter(
): PeriodValidationComponent {
    override fun endAt(shortenUrlLevel: ShortenUrlLevel, startAt: Instant): Instant {
        return startAt.plus(shortenUrlLevel.validPeriod)
    }
}
