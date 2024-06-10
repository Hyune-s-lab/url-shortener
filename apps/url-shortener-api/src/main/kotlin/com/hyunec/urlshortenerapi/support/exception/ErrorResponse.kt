package com.hyunec.urlshortenerapi.support.exception

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.Instant
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ErrorResponse(
    val code: ErrorCode,
    val message: String? = null,
    val data: Any? = null,
) {
    val timestamp: Instant = Instant.now()
    val traceId: String = UUID.randomUUID().toString()

    enum class ErrorCode(val desc: String) {
        // 특화 에러 코드
        INVALID_REQUEST_BODY("Request Body 가 유효하지 않음"),

        // 도메인 에러 코드
        NOT_FOUND_URL_KEY("해당 URL Key 가 존재하지 않음"),
        EXPIRED_SHORTEN_URL("만료된 단축 URL"),

        // 범용 에러 코드
        NOT_YET_DEFINED_ERROR("아직 정의되지 않은 에러"),
        UNEXPECTED_ERROR("예상하지 못한 에러")
        ;
    }
}
