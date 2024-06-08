package com.hyunec.infrastructure.mysql.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(
    basePackages = [
        "com.hyunec.infrastructure.mysql"
    ]
)
@EntityScan(
    "com.hyunec.infrastructure.mysql"
)
class JpaConfig {
}
