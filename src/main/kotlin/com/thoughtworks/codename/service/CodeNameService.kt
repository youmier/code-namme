package com.thoughtworks.codename.service

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.thoughtworks.codename.entity.Card
import com.thoughtworks.codename.entity.CodeNameCard
import com.thoughtworks.codename.entity.Type
import org.springframework.stereotype.Service
import java.io.File


@Service
class CodeNameService(
    private val translateService: TranslateService,
) {

    companion object {
        private const val REG = "[^/]+(?!.*/)"
    }

    fun parseAndOutput() {
        val mapper = jacksonObjectMapper()
        mapper.registerKotlinModule()
        mapper.registerModule(JavaTimeModule())

        val jsonString = File("./src/main/resources/codename.json").readText(Charsets.UTF_8)
        val cardList = mapper.readValue<List<CodeNameCard>>(jsonString).map {
            it.apply {
                id = (Regex(REG).find(id!!)?.value!!.toInt() + 1).toString()
            }
        }

        val result = mutableMapOf<String, Card>()

        cardList.map {
            if (result.containsKey(it.id)) {
                if (it.type == Type.coverCard) {
                    result[it.id]!!.color = it.data?.color
                } else {
                    result[it.id]!!.word = it.data?.word
                    result[it.id]!!.trans = translateService.translateInput(it.data?.word)
                }
            } else {
                result[it.id!!] = Card(
                    id = it.id,
                    color = it.data?.color,
                    word = it.data?.word,
                    trans = translateService.translateInput(it.data?.word)
                )
            }
        }

        val sorted = result.toSortedMap(compareBy<String> { it.toInt() }.thenBy { it })

        sorted.forEach {
            if ((it.key.toInt()) % 5 == 0) {
                println("||| ${it.value}")
            } else {
                print("||| ${it.value}")
            }
        }
    }

}