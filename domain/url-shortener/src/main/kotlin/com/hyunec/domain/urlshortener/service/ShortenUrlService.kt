package com.hyunec.domain.urlshortener.service

import com.hyunec.domain.shortenurl.ShortenUrl
import com.hyunec.domain.shortenurl.ShortenUrlCreate
import com.hyunec.domain.urlshortener.port.ShortenUrlOutputPort
import org.springframework.stereotype.Service

@Service
class ShortenUrlService(
    private val shortenUrlOutputPort: ShortenUrlOutputPort,
) {
    fun create(originalUrl: String): ShortenUrl {
        return shortenUrlOutputPort.save(ShortenUrlCreate(originalUrl))
    }

    fun findByUrlKey(urlKey: String): ShortenUrl {
        return shortenUrlOutputPort.findByUrlKey(urlKey) ?: throw RuntimeException("Not found")
    }
}
