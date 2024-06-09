package com.hyunec.urlshortenerapi.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
class IndexController {

    @GetMapping("/health")
    fun health(): Long {
        return Instant.now().toEpochMilli()
    }
}
