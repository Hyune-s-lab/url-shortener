package com.hyunec.urlshortenerapi.controller.shortenurl

import com.hyunec.domain.urlshortener.service.ShortenUrlService
import com.hyunec.urlshortenerapi.controller.shortenurl.request.ShortenUrlCreateRequest
import com.hyunec.urlshortenerapi.controller.shortenurl.response.ShortenUrlCreateResponse
import com.hyunec.urlshortenerapi.controller.shortenurl.response.ShortenUrlFindResponse
import com.hyunec.urlshortenerapi.support.util.KLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ShortenUrlController(
    private val shortenUrlService: ShortenUrlService,
) {
    @PostMapping("/api/v1/shorten-url")
    fun create(@RequestBody request: ShortenUrlCreateRequest): ShortenUrlCreateResponse {
        return shortenUrlService.create(request.url).run {
            ShortenUrlCreateResponse(
                url = this.originalUrl,
                urlKey = this.urlKey
            )
        }
    }

    @GetMapping("/api/v1/shorten-url/{urlKey}")
    fun findOriginUrl(@PathVariable urlKey: String): ShortenUrlFindResponse {
        return shortenUrlService.findByUrlKey(urlKey).run {
            ShortenUrlFindResponse(
                url = this.originalUrl,
                urlKey = this.urlKey
            )
        }
    }

    companion object: KLogging()
}
