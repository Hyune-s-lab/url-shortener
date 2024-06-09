package com.hyunec.domain.urlshortener.service

import com.hyunec.domain.urlshortener.ShortenUrl
import com.hyunec.domain.urlshortener.ShortenUrlCreate
import com.hyunec.domain.urlshortener.exception.ExpiredShortenUrlException
import com.hyunec.domain.urlshortener.exception.NotFoundUrlKeyException
import com.hyunec.domain.urlshortener.port.ShortenUrlOutputPort
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class ShortenUrlService(
    private val shortenUrlOutputPort: ShortenUrlOutputPort,
) {
    fun create(url: String): ShortenUrl {
        return shortenUrlOutputPort.save(ShortenUrlCreate(url))
    }

    fun findByUrlKey(urlkey: String): ShortenUrl {
        val shortenUrl = shortenUrlOutputPort.findByUrlKey(urlkey) ?: throw NotFoundUrlKeyException()

        if (shortenUrl.validEndAt < Instant.now()) {
            throw ExpiredShortenUrlException()
        }

        return shortenUrl
    }
}
