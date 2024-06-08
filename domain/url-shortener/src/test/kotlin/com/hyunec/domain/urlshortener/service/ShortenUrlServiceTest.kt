package com.hyunec.domain.urlshortener.service

import com.hyunec.common.support.KLogging
import com.hyunec.domain.urlshortener.AbstractUrlShortenerDomainTests
import com.hyunec.domain.urlshortener.exception.NotFoundUrlKeyException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.Test

class ShortenUrlServiceTest: AbstractUrlShortenerDomainTests() {

    private val shortenUrlOutputPortFakeAdapter: ShortenUrlOutputPortFakeAdapter = ShortenUrlOutputPortFakeAdapter()
    private val shortenUrlService: ShortenUrlService = ShortenUrlService(shortenUrlOutputPortFakeAdapter)

    @ParameterizedTest
    @MethodSource("validUrl")
    fun `shortenUrl 생성`(url: String) {
        shortenUrlService.create(url).let {
            it.originalUrl shouldBe url
            it.urlkey shouldNotBe null
        }
    }

    @ParameterizedTest
    @MethodSource("validUrl")
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
        @JvmStatic
        private fun validUrl(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    "https://www.google.com"
                )
            )
        }
    }
}
