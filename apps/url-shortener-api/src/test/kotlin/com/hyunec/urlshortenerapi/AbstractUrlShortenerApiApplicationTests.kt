package com.hyunec.urlshortenerapi

import com.fasterxml.jackson.databind.ObjectMapper
import com.hyunec.common.support.KLogging
import net.datafaker.Faker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.testcontainers.containers.GenericContainer
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
        val mysqlContainer = MySQLContainer("mysql:8.0.33").apply {
            withDatabaseName("url_shortener")
            withUsername("root")
            withPassword("u1234")
        }

        @JvmField
        val redisContainer = GenericContainer("redis:6.2.6").apply {
            exposedPorts = listOf(6379)
        }

        init {
            mysqlContainer.start()
            System.setProperty("spring.datasource.url", mysqlContainer.jdbcUrl)
            System.setProperty("spring.datasource.username", mysqlContainer.username)
            System.setProperty("spring.datasource.password", mysqlContainer.password)

            redisContainer.start()
            System.setProperty("spring.redis.host", redisContainer.host)
            System.setProperty("spring.redis.port", redisContainer.firstMappedPort.toString())
        }
    }
}
