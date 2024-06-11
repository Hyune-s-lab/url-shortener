package com.hyunec.domain.urlshortener.service

import com.hyunec.common.support.KLogging
import com.hyunec.domain.urlshortener.ShortenUrl
import com.hyunec.domain.urlshortener.ShortenUrlCreate
import com.hyunec.domain.urlshortener.component.PeriodValidationComponent
import com.hyunec.domain.urlshortener.exception.ExpiredShortenUrlException
import com.hyunec.domain.urlshortener.exception.NotFoundUrlKeyException
import com.hyunec.domain.urlshortener.model.ShortenUrlLevel
import com.hyunec.domain.urlshortener.port.ShortenUrlOutputPort
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class ShortenUrlService(
    @Qualifier("shortenUrlOutputPortJpaAdapter") private val shortenUrlOutputPortJpa: ShortenUrlOutputPort,
    @Qualifier("shortenUrlOutputPortRedisAdapter") private val shortenUrlOutputPortRedis: ShortenUrlOutputPort,
    private val periodValidationComponent: PeriodValidationComponent,
) {
    fun create(shortenUrlLevel: ShortenUrlLevel, url: String): ShortenUrl {
        val validStartAt = Instant.now()
        val validEndAt = periodValidationComponent.endAt(shortenUrlLevel, validStartAt)
        return shortenUrlOutputPortJpa.save(
            ShortenUrlCreate(
                level = shortenUrlLevel,
                originalUrl = url,
                validStartAt = validStartAt,
                validEndAt = validEndAt
            )
        )
    }

    fun findByUrlKey(urlkey: String): ShortenUrl {
        val shortenUrl = shortenUrlOutputPortRedis.findByUrlKey(urlkey)
            ?: shortenUrlOutputPortJpa.findByUrlKey(urlkey)?.also {
                shortenUrlOutputPortRedis.save(it)
            } ?: throw NotFoundUrlKeyException()

        if (shortenUrl.validEndAt < Instant.now()) {
            throw ExpiredShortenUrlException()
        }

        return shortenUrl
    }

    companion object: KLogging()
}
