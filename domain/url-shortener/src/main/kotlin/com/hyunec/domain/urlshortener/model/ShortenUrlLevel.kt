package com.hyunec.domain.urlshortener.model

import java.time.Period

/**
 * ShortenUrl 의 등급
 */
enum class ShortenUrlLevel(val validPeriod: Period) {
    FREE(Period.ofDays(1)),
    BASIC(Period.ofDays(31)),
    PREMIUM(Period.ofDays(366 * 1000)), // unlimited
    ;
}
