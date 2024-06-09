package com.hyunec.domain.urlshortener.exception

class NotFoundUrlKeyException: RuntimeException {
    constructor(): super(DEFAULT_MESSAGE)
    constructor(message: String): super(message)

    companion object {
        const val DEFAULT_MESSAGE = "Not found url key"
    }
}
