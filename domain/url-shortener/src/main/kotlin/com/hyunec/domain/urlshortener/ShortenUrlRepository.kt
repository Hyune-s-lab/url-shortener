package com.hyunec.domain.shortenurl

interface ShortenUrlRepository {
    fun save(shortenUrlCreate: ShortenUrlCreate): ShortenUrl
    fun findByUrlKey(urlKey: String): ShortenUrl?
}
