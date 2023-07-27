package com.zetung.zetpass.ui

class ParserView {
    fun parseStringToMap(input: String, keyValueDelimiter: Char, pairDelimiter: Char): MutableMap<String, String> {
        val resultMap = mutableMapOf<String, String>()
        val pairs = input.split(pairDelimiter)
        for (pair in pairs) {
            val keyValue = pair.split(keyValueDelimiter)
            if (keyValue.size == 2) {
                val key = keyValue[0].trim()
                val value = keyValue[1].trim()
                resultMap[key] = value
            }
        }
        return resultMap
    }
}