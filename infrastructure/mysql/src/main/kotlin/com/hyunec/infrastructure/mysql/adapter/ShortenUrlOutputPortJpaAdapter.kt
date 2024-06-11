package com.hyunec.infrastructure.mysql.adapter

import com.hyunec.common.support.KLogging
import com.hyunec.common.support.decodeBase62
import com.hyunec.common.support.encodeBase62
import com.hyunec.domain.urlshortener.ShortenUrl
import com.hyunec.domain.urlshortener.ShortenUrlCreate
import com.hyunec.domain.urlshortener.port.ShortenUrlOutputPort
import com.hyunec.infrastructure.mysql.entity.ShortenUrlEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ShortenUrlOutputPortJpaAdapter(
    private val repository: ShortenUrlJpaRepository,
): ShortenUrlOutputPort {
    @Transactional
    override fun save(shortenUrlCreate: ShortenUrlCreate): ShortenUrl {
        return repository.save(
            ShortenUrlEntity(
                level = shortenUrlCreate.level,
                url = shortenUrlCreate.originalUrl,
                validStartAt = shortenUrlCreate.validStartAt,
                validEndAt = shortenUrlCreate.validEndAt
            )
        ).toModel()
    }

    @Transactional(readOnly = true)
    override fun findByUrlKey(urlkey: String): ShortenUrl? {
        val id = urlkey.decodeBase62().toLong()
        return repository.findByIdOrNull(id)?.toModel()
    }

    private fun ShortenUrlEntity.toModel(): ShortenUrl {
        val idByteArray = this.id.toString().toByteArray()
        return ShortenUrl(
            id = this.id!!,
            level = this.level,
            originalUrl = this.url,
            urlkey = idByteArray.encodeBase62(),
            validStartAt = this.validStartAt,
            validEndAt = this.validEndAt
        )
    }

    companion object: KLogging()
}
