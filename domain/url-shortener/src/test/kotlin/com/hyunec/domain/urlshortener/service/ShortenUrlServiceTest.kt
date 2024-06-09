package com.hyunec.domain.urlshortener.service

import com.hyunec.common.support.KLogging
import com.hyunec.domain.urlshortener.AbstractUrlShortenerDomainTests
import com.hyunec.domain.urlshortener.exception.NotFoundUrlKeyException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.Test

class ShortenUrlServiceTest: AbstractUrlShortenerDomainTests() {

    private lateinit var shortenUrlOutputPortFakeAdapter: ShortenUrlOutputPortFakeAdapter
    private lateinit var shortenUrlService: ShortenUrlService

    @BeforeEach
    fun beforeEach() {
        shortenUrlOutputPortFakeAdapter = ShortenUrlOutputPortFakeAdapter()
        shortenUrlService = ShortenUrlService(shortenUrlOutputPortFakeAdapter)
    }

    @ParameterizedTest
    @MethodSource("validUrls")
    fun `shortenUrl 생성`(url: String) {
        shortenUrlService.create(url).let {
            it.originalUrl shouldBe url
            it.urlkey shouldNotBe null
        }
    }

    @Test
    fun `shortenUrl 생성 - 복수 개수`() {
        val shortenUrls = validUrls.map { shortenUrlService.create(it) }
        shortenUrls.forEachIndexed { index, shortenUrl ->
            shortenUrl.originalUrl shouldBe validUrls[index]
        }
    }

    @ParameterizedTest
    @MethodSource("validUrls")
    fun `shortenUrl 조회`(url: String) {
        val urlkey = shortenUrlService.create(url).urlkey
        shortenUrlService.findByUrlKey(urlkey).let {
            it.originalUrl shouldBe url
            it.urlkey shouldBe urlkey
        }
    }

    @Test
    fun `shortenUrl 조회 - 존재하지 않는 urlkey`() {
        val urlkey = "MA==" // 0
        shouldThrow<NotFoundUrlKeyException> {
            shortenUrlService.findByUrlKey(urlkey)
        }.message shouldBe NotFoundUrlKeyException.DEFAULT_MESSAGE
    }

    companion object: KLogging() {
        private val validUrls: List<String> = datafaker.collection(
            { datafaker.internet().url() }
        ).generate()

        @JvmStatic
        private fun validUrls(): Stream<Arguments> {
            return validUrls.stream().map {
                Arguments.of(it.toString())
            }
        }
    }
}
