package com.hyunec.urlshortenerapi.controller.shortenurl

import com.hyunec.common.support.KLogging
import com.hyunec.domain.urlshortener.service.ShortenUrlService
import com.hyunec.urlshortenerapi.controller.shortenurl.request.ShortenUrlCreateRequest
import com.hyunec.urlshortenerapi.controller.shortenurl.response.ShortenUrlCreateResponse
import com.hyunec.urlshortenerapi.controller.shortenurl.response.ShortenUrlFindResponse
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
                urlkey = this.urlkey
            )
        }
    }

    @GetMapping("/api/v1/shorten-url/{urlkey}")
    fun findOriginUrl(@PathVariable urlkey: String): ShortenUrlFindResponse {
        return shortenUrlService.findByUrlKey(urlkey).run {
            ShortenUrlFindResponse(
                url = this.originalUrl,
                urlkey = this.urlkey
            )
        }
    }

    companion object: KLogging()
}
