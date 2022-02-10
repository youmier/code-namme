package com.thoughtworks.codename.service

import com.thoughtworks.codename.entity.TranslateResponse
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate


@Service
class TranslateService(
    private val restTemplate: RestTemplate
) {

    companion object {
        var googleTranslationUrl =
            "https://translate.google.com/translate_a/single?client=at&dt=t&dt=ld&dt=qca&dt=rm&dt=bd&dj=1&hl=%25s&ie=UTF-8&oe=UTF-8&inputm=2&otf=2&iid=1dd3b944-fa62-4b55-b330-74909a99969e&"
    }

    fun translateInput(word: String?): String {
        val headers = HttpHeaders()
        headers.accept = listOf(MediaType.APPLICATION_FORM_URLENCODED)
        headers.set("User-Agent", "AndroidTranslate/5.3.0.RC02.130475354-53000263 5.1 phone TRANSLATE_OPM5_TEST_1")
        val map: MultiValueMap<String, String> = LinkedMultiValueMap()
        map.add("sl", "en")
        map.add("tl", "zh-CN")
        map.add("q", word)
        val entity = HttpEntity(map, headers)
        val response: ResponseEntity<TranslateResponse> = restTemplate.exchange(
            googleTranslationUrl, HttpMethod.POST, entity,
            TranslateResponse::class.java
        )
        return response.body?.sentences?.get(0)?.trans ?: ""
    }

}