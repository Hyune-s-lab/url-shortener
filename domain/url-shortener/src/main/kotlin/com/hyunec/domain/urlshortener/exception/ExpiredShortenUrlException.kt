package com.hyunec.domain.urlshortener.exception

class ExpiredShortenUrlException: RuntimeException {
    constructor(): super(DEFAULT_MESSAGE)
    constructor(message: String): super(message)

    companion object {
        const val DEFAULT_MESSAGE = "Expired Shorten URL"
    }
}
