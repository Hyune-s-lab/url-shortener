package com.hyunec.urlshortenerapi

import com.fasterxml.jackson.databind.ObjectMapper
import com.hyunec.common.support.KLogging
import net.datafaker.Faker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles(value = ["test"])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class AbstractUrlShortenerApiApplicationTests {

    @Autowired
    lateinit var objectMapper: ObjectMapper

    companion object: KLogging() {
        @JvmStatic
        protected val datafaker = Faker()
    }
}
