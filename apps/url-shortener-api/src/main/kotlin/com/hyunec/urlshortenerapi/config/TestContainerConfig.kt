package com.hyunec.urlshortenerapi.config

import com.hyunec.common.support.KLogging
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.core.annotation.Order
import org.testcontainers.containers.MySQLContainer

@Profile("local")
@Configuration
@Order(0)
class TestContainerConfig {

    companion object: KLogging() {
        @JvmField
        val container = MySQLContainer("mysql:8.0.33").apply {
            withDatabaseName("url_shortener")
            withUsername("root")
            withPassword("u1234")
            setPortBindings(listOf("23306:3306"))
        }

        init {
            container.start()
            System.setProperty("spring.datasource.url", container.jdbcUrl)
            System.setProperty("spring.datasource.username", container.username)
            System.setProperty("spring.datasource.password", container.password)
        }
    }
}
