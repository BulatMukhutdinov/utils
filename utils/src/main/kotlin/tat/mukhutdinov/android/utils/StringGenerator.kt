package tat.mukhutdinov.android.utils

import java.util.*

class StringGenerator {

    fun generateUuid() =
        UUID.randomUUID().toString()

    fun generateAlphaNumeric(length: Int = 8): String {
        val digits = IntRange(48, 57)
        val lowerCaseLetters = IntRange(97, 122)
        val upperCaseLetters = IntRange(65, 90)

        val randomSymbols = digits.toList() + lowerCaseLetters.toList() + upperCaseLetters.toList()

        var randomString = ""

        for (i in 1..length) {
            randomString += randomSymbols.random().toChar()
        }

        return randomString
    }
}