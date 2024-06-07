package com.hyunec.domain.urlshortener.port

import com.hyunec.domain.shortenurl.ShortenUrl
import com.hyunec.domain.shortenurl.ShortenUrlCreate
import org.springframework.stereotype.Component
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@Component
class ShortenUrlOutputPortFakeAdapter(
    private val map: MutableMap<Long, ShortenUrl> = mutableMapOf(),
): ShortenUrlOutputPort {
    @OptIn(ExperimentalEncodingApi::class)
    override fun save(shortenUrlCreate: ShortenUrlCreate): ShortenUrl {
        val id = map.size.toLong() + 1
        val shortenUrl = ShortenUrl(
            originalUrl = shortenUrlCreate.originalUrl,
            urlKey = Base64.UrlSafe.encode(id.toString().toByteArray(), 0, id.toString().toByteArray().size)
        )
        map[id] = shortenUrl
        return shortenUrl
    }

    @OptIn(ExperimentalEncodingApi::class)
    override fun findByUrlKey(urlKey: String): ShortenUrl? {
        val bytes = Base64.UrlSafe.decode(urlKey.toByteArray(), 0, urlKey.toByteArray().size)
        val id = String(bytes, Charsets.UTF_8).toLong()
        return map[id]
    }
}
