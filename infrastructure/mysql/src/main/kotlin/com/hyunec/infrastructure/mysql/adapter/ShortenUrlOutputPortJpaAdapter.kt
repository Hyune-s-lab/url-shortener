package com.hyunec.infrastructure.mysql.adapter

import com.hyunec.common.support.KLogging
import com.hyunec.domain.urlshortener.ShortenUrl
import com.hyunec.domain.urlshortener.ShortenUrlCreate
import com.hyunec.domain.urlshortener.port.ShortenUrlOutputPort
import com.hyunec.infrastructure.mysql.entity.ShortenUrlEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@Component
class ShortenUrlOutputPortJpaAdapter(
    private val repository: ShortenUrlJpaRepository,
): ShortenUrlOutputPort {
    @Transactional
    override fun save(shortenUrlCreate: ShortenUrlCreate): ShortenUrl {
        return repository.save(
            ShortenUrlEntity(url = shortenUrlCreate.originalUrl)
        ).toModel()
    }

    @Transactional(readOnly = true)
    override fun findByUrlKey(urlkey: String): ShortenUrl? {
        val id = urlkey.base64UrlSafeDecode().toLong()

        return repository.findByIdOrNull(id)?.toModel()
    }

    @OptIn(ExperimentalEncodingApi::class)
    private fun ShortenUrlEntity.toModel(): ShortenUrl {
        val idByteArray = this.id.toString().toByteArray()
        return ShortenUrl(
            originalUrl = this.url,
            urlkey = Base64.UrlSafe.encode(idByteArray, 0, idByteArray.size)
        )
    }

    @OptIn(ExperimentalEncodingApi::class)
    private fun String.base64UrlSafeDecode(): String {
        val urlKeyByteArray = this.toByteArray()
        return Base64.UrlSafe.decode(urlKeyByteArray, 0, urlKeyByteArray.size)
            .toString(Charsets.UTF_8)
    }

    companion object: KLogging()
}
