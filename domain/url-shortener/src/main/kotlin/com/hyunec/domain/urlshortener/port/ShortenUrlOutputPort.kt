package com.hyunec.domain.urlshortener.port

import com.hyunec.domain.urlshortener.ShortenUrl
import com.hyunec.domain.urlshortener.ShortenUrlCreate

interface ShortenUrlOutputPort {
    fun save(shortenUrlCreate: ShortenUrlCreate): ShortenUrl{
        throw UnsupportedOperationException("Not implemented yet")
    }

    fun save(shortenUrl: ShortenUrl): ShortenUrl {
        throw UnsupportedOperationException("Not implemented yet")
    }

    fun findByUrlKey(urlkey: String): ShortenUrl?
}
