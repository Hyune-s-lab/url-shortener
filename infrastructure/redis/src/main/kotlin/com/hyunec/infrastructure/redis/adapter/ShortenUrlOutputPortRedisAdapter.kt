package com.hyunec.infrastructure.redis.adapter

import com.hyunec.common.support.KLogging
import com.hyunec.common.support.decodeBase62
import com.hyunec.common.support.encodeBase62
import com.hyunec.domain.urlshortener.ShortenUrl
import com.hyunec.domain.urlshortener.port.ShortenUrlOutputPort
import com.hyunec.infrastructure.redis.entity.ShortenUrlEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class ShortenUrlOutputPortRedisAdapter(
    private val repository: ShortenUrlRedisRepository,
): ShortenUrlOutputPort {
    override fun save(shortenUrl: ShortenUrl): ShortenUrl {
        return repository.save(
            ShortenUrlEntity(
                id = shortenUrl.id,
                level = shortenUrl.level,
                url = shortenUrl.originalUrl,
                validStartAt = shortenUrl.validStartAt,
                validEndAt = shortenUrl.validEndAt
            )
        ).toModel()
    }

    override fun findByUrlKey(urlkey: String): ShortenUrl? {
        val id = urlkey.decodeBase62()
        return repository.findByIdOrNull(id)?.toModel()
    }

    private fun ShortenUrlEntity.toModel(): ShortenUrl {
        val idByteArray = this.id.toString().toByteArray()
        return ShortenUrl(
            id = this.id,
            level = this.level,
            originalUrl = this.url,
            urlkey = idByteArray.encodeBase62(),
            validStartAt = this.validStartAt,
            validEndAt = this.validEndAt
        )
    }

    companion object: KLogging()
}
