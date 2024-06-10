package com.hyunec.urlshortenerapi

import com.fasterxml.jackson.databind.ObjectMapper
import com.hyunec.common.support.KLogging
import net.datafaker.Faker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.MySQLContainer

@ActiveProfiles(value = ["test"])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class AbstractUrlShortenerApiApplicationTests {

    @Autowired
    lateinit var objectMapper: ObjectMapper

    companion object: KLogging() {
        @JvmStatic
        protected val datafaker = Faker()

        @JvmField
        val container = MySQLContainer("mysql:8.0.33").apply {
            withDatabaseName("url_shortener")
            withUsername("root")
            withPassword("u1234")
        }

        init {
            container.start()
            System.setProperty("spring.datasource.url", container.jdbcUrl)
            System.setProperty("spring.datasource.username", container.username)
            System.setProperty("spring.datasource.password", container.password)
        }
    }
}
