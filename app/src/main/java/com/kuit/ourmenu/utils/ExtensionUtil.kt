package com.kuit.ourmenu.utils

import java.util.Locale

object ExtensionUtil {
    fun Int.toWon(): String {
        val decimalFormat = java.text.DecimalFormat("#,###")
        val formatted = decimalFormat.format(this)

        return "${formatted}원"
    }

    fun Int.toMealTime(): String = String.format(Locale.ROOT, "%02d", this) + ":00"

}