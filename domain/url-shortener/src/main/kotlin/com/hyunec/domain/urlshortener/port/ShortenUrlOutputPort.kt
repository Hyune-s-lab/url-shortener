package com.hyunec.domain.urlshortener.port

import com.hyunec.domain.shortenurl.ShortenUrl
import com.hyunec.domain.shortenurl.ShortenUrlCreate

interface ShortenUrlOutputPort {
    fun save(shortenUrlCreate: ShortenUrlCreate): ShortenUrl
    fun findByUrlKey(urlKey: String): ShortenUrl?
}
