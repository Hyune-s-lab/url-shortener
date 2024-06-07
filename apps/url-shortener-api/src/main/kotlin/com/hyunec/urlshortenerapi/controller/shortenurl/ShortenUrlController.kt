package com.hyunec.urlshortenerapi.controller.shortenurl

import com.hyunec.urlshortenerapi.controller.shortenurl.request.ShortenUrlCreateRequest
import com.hyunec.urlshortenerapi.controller.shortenurl.response.ShortenUrlCreateResponse
import com.hyunec.urlshortenerapi.controller.shortenurl.response.ShortenUrlFindResponse
import com.hyunec.urlshortenerapi.support.util.KLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class ShortenUrlController {

    @PostMapping("/api/v1/shorten-url")
    fun create(@RequestBody request: ShortenUrlCreateRequest): ShortenUrlCreateResponse {
        log.debug("request={}", request)
        return ShortenUrlCreateResponse(
            url = request.url,
            urlKey = UUID.randomUUID().toString().replace("-", "")
        )
    }

    @GetMapping("/api/v1/shorten-url/{urlKey}")
    fun findOriginUrl(@PathVariable urlKey: String): ShortenUrlFindResponse {
        return ShortenUrlFindResponse(
            url = "https://www.google.com",
            urlKey = urlKey
        )
    }

    companion object: KLogging()
}
