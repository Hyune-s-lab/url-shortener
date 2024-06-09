package com.hyunec.domain.urlshortener.component

import com.hyunec.domain.urlshortener.AbstractUrlShortenerDomainTests
import com.hyunec.domain.urlshortener.model.ShortenUrlLevel
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import java.time.Instant

class PeriodValidationComponentAdapterTest: AbstractUrlShortenerDomainTests() {

    private val periodValidationComponentAdapter = PeriodValidationComponentAdapter()

    @ParameterizedTest
    @EnumSource(ShortenUrlLevel::class)
    fun `endAt 생성`(shortenUrlLevel: ShortenUrlLevel) {
        val startAt = Instant.now()
        val endAt = periodValidationComponentAdapter.endAt(shortenUrlLevel, startAt)

        endAt shouldBe startAt.plus(shortenUrlLevel.validPeriod)
    }
}
