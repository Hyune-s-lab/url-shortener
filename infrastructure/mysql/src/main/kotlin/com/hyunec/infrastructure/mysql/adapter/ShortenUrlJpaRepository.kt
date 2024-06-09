package com.hyunec.infrastructure.mysql.adapter

import com.hyunec.infrastructure.mysql.entity.ShortenUrlEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ShortenUrlJpaRepository: JpaRepository<ShortenUrlEntity, Long>
