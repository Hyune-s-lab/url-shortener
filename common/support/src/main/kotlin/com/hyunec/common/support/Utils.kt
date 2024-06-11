package com.hyunec.common.support

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@OptIn(ExperimentalEncodingApi::class)
fun ByteArray.encodeBase62(): String {
    return Base64.UrlSafe.encode(this, 0, this.size)
}

@OptIn(ExperimentalEncodingApi::class)
fun String.decodeBase62(): String {
    val urlKeyByteArray = this.toByteArray()
    return Base64.UrlSafe.decode(urlKeyByteArray, 0, urlKeyByteArray.size)
        .toString(Charsets.UTF_8)
}

