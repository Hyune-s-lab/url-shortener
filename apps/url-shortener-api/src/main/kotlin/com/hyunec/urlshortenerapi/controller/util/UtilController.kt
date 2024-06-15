package com.hyunec.urlshortenerapi.controller.util

import com.hyunec.common.support.KLogging
import com.hyunec.domain.urlshortener.service.ShortenUrlService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UtilController(
    private val shortenUrlService: ShortenUrlService,
) {
    @GetMapping("/api/v1/shorten-url/{urlkey}/evict")
    fun evict(@PathVariable urlkey: String) {
        shortenUrlService.cacheEvict(urlkey)
    }

    companion object: KLogging()
}
