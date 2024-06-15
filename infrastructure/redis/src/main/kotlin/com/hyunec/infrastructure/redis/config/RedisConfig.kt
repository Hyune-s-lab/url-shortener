package com.hyunec.infrastructure.redis.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.hyunec.domain.urlshortener.ShortenUrl
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

@EnableCaching
@Configuration
@EnableRedisRepositories(
    basePackages = [
        "com.hyunec.infrastructure.redis"
    ]
)
@EntityScan(
    "com.hyunec.infrastructure.redis"
)
class RedisConfig(
    @Value("\${spring.redis.host}") val redisHost: String,
    @Value("\${spring.redis.port}") val redisPort: Int,
) {

    @Bean
    fun connectionFactory(): LettuceConnectionFactory {
        return LettuceConnectionFactory(redisHost, redisPort)
    }

    @Bean
    fun shortenUrlCacheManager(): CacheManager {
        val ttl = Duration.ofHours(24)
        val keySerializer = SerializationPair.fromSerializer(StringRedisSerializer())
        val valueSerializer = SerializationPair.fromSerializer(
            Jackson2JsonRedisSerializer(redisObjectMapper(), ShortenUrl::class.java)
        )

        return RedisCacheManager.builder(connectionFactory())
            .cacheDefaults(
                RedisCacheConfiguration.defaultCacheConfig()
                    .entryTtl(ttl)
                    .serializeKeysWith(keySerializer)
                    .serializeValuesWith(valueSerializer)
            )
            .build()
    }

    private fun redisObjectMapper(): ObjectMapper {
        return ObjectMapper().apply {
            registerModule(kotlinModule())
            registerModule(JavaTimeModule())

            activateDefaultTyping(
                BasicPolymorphicTypeValidator.builder()
                    .allowIfBaseType(Any::class.java)
                    .build(), ObjectMapper.DefaultTyping.NON_FINAL
            )
        }
    }
}
