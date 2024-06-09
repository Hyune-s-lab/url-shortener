package com.hyunec.domain.urlshortener.port

import com.hyunec.domain.urlshortener.ShortenUrl
import com.hyunec.domain.urlshortener.ShortenUrlCreate

interface ShortenUrlOutputPort {
    fun save(shortenUrlCreate: ShortenUrlCreate): ShortenUrl
    fun findByUrlKey(urlkey: String): ShortenUrl?
}
