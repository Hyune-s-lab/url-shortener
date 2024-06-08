package com.hyunec.domain.urlshortener.service

import com.hyunec.common.support.KLogging
import com.hyunec.domain.urlshortener.AbstractUrlShortenerDomainTests
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ShortenUrlServiceTest: AbstractUrlShortenerDomainTests() {

    private val shortenUrlOutputPortFakeAdapter: ShortenUrlOutputPortFakeAdapter = ShortenUrlOutputPortFakeAdapter()
    private val shortenUrlService: ShortenUrlService = ShortenUrlService(shortenUrlOutputPortFakeAdapter)

    @ParameterizedTest
    @MethodSource("validUrl")
    fun `shortenUrl 생성`(url: String) {
        shortenUrlService.create(url).let {
            it.originalUrl shouldBe url
            it.urlKey shouldNotBe null
        }
    }

    @ParameterizedTest
    @MethodSource("validUrl")
    fun `shortenUrl 조회`(url: String) {
        val urlKey = shortenUrlService.create(url).urlKey
        shortenUrlService.findByUrlKey(urlKey).let {
            it.originalUrl shouldBe url
            it.urlKey shouldBe urlKey
        }
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
