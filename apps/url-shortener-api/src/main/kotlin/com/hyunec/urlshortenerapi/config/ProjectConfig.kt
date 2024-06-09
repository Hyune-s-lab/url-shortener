package com.hyunec.urlshortenerapi.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(
    basePackages = [
        "com.hyunec.urlshortenerapi",
        "com.hyunec.domain",
        "com.hyunec.infrastructure",
    ]
)
class ProjectConfig
