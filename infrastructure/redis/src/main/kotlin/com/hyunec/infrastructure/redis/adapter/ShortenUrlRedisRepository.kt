package com.hyunec.infrastructure.redis.adapter

import com.hyunec.infrastructure.redis.entity.ShortenUrlEntity
import org.springframework.data.repository.CrudRepository

interface ShortenUrlRedisRepository: CrudRepository<ShortenUrlEntity, String>
