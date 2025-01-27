package com.kuit.ourmenu.utils

object ExtensionUtil {
    fun Int.toWon(): String {
        val decimalFormat = java.text.DecimalFormat("#,###")
        val formatted = decimalFormat.format(this)

        return "${formatted}원"
    }
}