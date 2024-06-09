package com.hyunec.urlshortenerapi.controller.shortenurl.request

import com.hyunec.domain.urlshortener.model.ShortenUrlLevel

data class ShortenUrlCreateRequest(
    val level: ShortenUrlLevel,
    val url: String,
)
