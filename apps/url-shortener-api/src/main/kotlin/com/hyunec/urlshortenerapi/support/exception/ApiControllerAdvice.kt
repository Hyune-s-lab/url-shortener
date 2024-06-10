package com.hyunec.urlshortenerapi.support.exception

import com.hyunec.common.support.KLogging
import com.hyunec.domain.urlshortener.exception.ExpiredShortenUrlException
import com.hyunec.domain.urlshortener.exception.NotFoundUrlKeyException
import com.hyunec.urlshortenerapi.support.exception.ErrorResponse.ErrorCode.EXPIRED_SHORTEN_URL
import com.hyunec.urlshortenerapi.support.exception.ErrorResponse.ErrorCode.INVALID_REQUEST_BODY
import com.hyunec.urlshortenerapi.support.exception.ErrorResponse.ErrorCode.NOT_FOUND_URL_KEY
import com.hyunec.urlshortenerapi.support.exception.ErrorResponse.ErrorCode.UNEXPECTED_ERROR
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiControllerAdvice(
) {
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleInvalidRequestBodyException(e: MethodArgumentNotValidException): ErrorResponse {
        return ErrorResponse(
            code = INVALID_REQUEST_BODY,
            message = e.message,
            data = e.bindingResult.allErrors.map { error ->
                val objectName = if (error is FieldError) error.field else error.objectName
                mapOf(objectName to error.defaultMessage)
            }
        ).also { log.warn(e.message, e) }
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ErrorResponse {
        return ErrorResponse(code = INVALID_REQUEST_BODY, message = e.message)
            .also { log.warn(e.message, e) }
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalStateException(e: IllegalStateException): ErrorResponse {
        return ErrorResponse(code = INVALID_REQUEST_BODY, message = e.message)
            .also { log.warn(e.message, e) }
    }


    // domain exception


    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(NotFoundUrlKeyException::class)
    fun handleNotFoundUrlKeyException(e: NotFoundUrlKeyException): ErrorResponse {
        return ErrorResponse(code = NOT_FOUND_URL_KEY)
            .also { log.warn(e.message, e) }
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ExpiredShortenUrlException::class)
    fun handleExpiredShortenUrlException(e: ExpiredShortenUrlException): ErrorResponse {
        return ErrorResponse(code = EXPIRED_SHORTEN_URL)
            .also { log.warn(e.message, e) }
    }


    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ErrorResponse {
        return ErrorResponse(code = UNEXPECTED_ERROR, message = e.message)
            .also { log.error(e.message, e) }
    }

    companion object: KLogging()
}
