package com.hyunec.domain.urlshortener.service

import com.hyunec.common.support.decodeBase62
import com.hyunec.common.support.encodeBase62
import com.hyunec.domain.urlshortener.ShortenUrl
import com.hyunec.domain.urlshortener.ShortenUrlCreate
import com.hyunec.domain.urlshortener.port.ShortenUrlOutputPort

class ShortenUrlOutputPortFakeAdapter(
    private val map: MutableMap<Long, ShortenUrl> = mutableMapOf(),
): ShortenUrlOutputPort {
    override fun save(shortenUrlCreate: ShortenUrlCreate): ShortenUrl {
        val id = map.size.toLong() + 1
        val idByteArray = id.toString().toByteArray()
        val shortenUrl = ShortenUrl(
            id = id,
            level = shortenUrlCreate.level,
            originalUrl = shortenUrlCreate.originalUrl,
            urlkey = idByteArray.encodeBase62(),
            validStartAt = shortenUrlCreate.validStartAt,
            validEndAt = shortenUrlCreate.validEndAt,
        )
        map[id] = shortenUrl
        return shortenUrl
    }

    override fun findByUrlKey(urlkey: String): ShortenUrl? {
        val id = urlkey.decodeBase62().toLong()
        return map[id]
    }
}
