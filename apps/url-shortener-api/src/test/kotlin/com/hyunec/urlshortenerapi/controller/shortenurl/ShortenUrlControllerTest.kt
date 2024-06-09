package com.hyunec.urlshortenerapi.controller.shortenurl

import com.hyunec.common.support.KLogging
import com.hyunec.urlshortenerapi.AbstractUrlShortenerApiApplicationTests
import com.hyunec.urlshortenerapi.controller.shortenurl.request.ShortenUrlCreateRequest
import com.hyunec.urlshortenerapi.controller.shortenurl.response.ShortenUrlFindResponse
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.stream.Stream

@AutoConfigureMockMvc
class ShortenUrlControllerTest(
    @Autowired private val mockMvc: MockMvc,
): AbstractUrlShortenerApiApplicationTests() {

    @ParameterizedTest
    @MethodSource("validCreateRequest")
    fun `shortenUrl 생성`(request: ShortenUrlCreateRequest) {
        val result = mockMvc.perform(
            post("/api/v1/shorten-url")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )

        result
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.url").value(request.url))
            .andExpect(jsonPath("$.urlkey").isString)
            .andDo { log.debug("response={}", it.response.contentAsString) }
    }

    @ParameterizedTest
    @MethodSource("validCreateRequest")
    fun `shortenUrl 조회`(request: ShortenUrlCreateRequest) {
        val urlkey = mockMvc.perform(
            post("/api/v1/shorten-url")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andReturn()
            .let { objectMapper.readValue(it.response.contentAsString, ShortenUrlFindResponse::class.java) }
            .urlkey
        val result = mockMvc.perform(
            get("/api/v1/shorten-url/${urlkey}")
                .contentType(MediaType.APPLICATION_JSON)
        )

        result
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.url").value(request.url))
            .andExpect(jsonPath("$.urlkey").value(urlkey))
            .andDo { log.debug("response={}", it.response.contentAsString) }
    }

    companion object: KLogging() {
        private val validUrls: List<String> = datafaker.collection(
            { datafaker.internet().url() }
        ).generate()

        @JvmStatic
        private fun validCreateRequest(): Stream<Arguments> {
            return validUrls.stream().map {
                Arguments.of(ShortenUrlCreateRequest(url = it))
            }
        }
    }
}
