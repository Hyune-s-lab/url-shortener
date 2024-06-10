package com.hyunec.domain.urlshortener.service

import com.hyunec.domain.urlshortener.ShortenUrl
import com.hyunec.domain.urlshortener.ShortenUrlCreate
import com.hyunec.domain.urlshortener.component.PeriodValidationComponent
import com.hyunec.domain.urlshortener.exception.ExpiredShortenUrlException
import com.hyunec.domain.urlshortener.exception.NotFoundUrlKeyException
import com.hyunec.domain.urlshortener.model.ShortenUrlLevel
import com.hyunec.domain.urlshortener.port.ShortenUrlOutputPort
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class ShortenUrlService(
    private val shortenUrlOutputPort: ShortenUrlOutputPort,
    private val periodValidationComponent: PeriodValidationComponent,
) {
    fun create(shortenUrlLevel: ShortenUrlLevel, url: String): ShortenUrl {
        val validStartAt = Instant.now()
        val validEndAt = periodValidationComponent.endAt(shortenUrlLevel, validStartAt)
        return shortenUrlOutputPort.save(
            ShortenUrlCreate(
                level = shortenUrlLevel,
                originalUrl = url,
                validStartAt = validStartAt,
                validEndAt = validEndAt
            )
        )
    }

    fun findByUrlKey(urlkey: String): ShortenUrl {
        val shortenUrl = shortenUrlOutputPort.findByUrlKey(urlkey) ?: throw NotFoundUrlKeyException()

        if (shortenUrl.validEndAt < Instant.now()) {
            throw ExpiredShortenUrlException()
        }

        return shortenUrl
    }
}
