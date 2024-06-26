package com.hyunec.urlshortenerapi.controller.shortenurl.response

data class ShortenUrlFindResponse(
    val url: String,
    val urlkey: String,
    val validStartAt: Long,
    val validEndAt: Long,
)
