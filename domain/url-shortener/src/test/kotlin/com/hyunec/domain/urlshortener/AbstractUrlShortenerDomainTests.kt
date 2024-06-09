package com.hyunec.domain.urlshortener

import com.hyunec.common.support.KLogging
import net.datafaker.Faker

abstract class AbstractUrlShortenerDomainTests {
    companion object: KLogging() {
        @JvmStatic
        protected val datafaker = Faker()
    }
}
