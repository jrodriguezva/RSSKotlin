package com.jrodiriguezva.rsskotlin.utils.extension

import java.text.SimpleDateFormat
import java.util.*

fun Date.toString(pattern: String): String {
    val format = SimpleDateFormat(pattern, Locale.getDefault())
    return format.format(this)
}

val Date.numberDay: Int
    get() {
        val cal = Calendar.getInstance()
        cal.time = this
        return cal.get(Calendar.DAY_OF_MONTH)
    }


val Date.nameDay: String
    get() {
        return this.toString("EEE")
    }

val Date.nameMonth: String
    get() {
        return this.toString("MMM")
    }

val Date.hourString: String
    get() {
        return this.toString("HH:mm")
    }


val Date.numberMonth: Int
    get() {
        val cal = Calendar.getInstance()
        cal.time = this
        return cal.get(Calendar.MONTH)
    }


val Date.numberYear: Int
    get() {
        val cal = Calendar.getInstance()
        cal.time = this
        return cal.get(Calendar.YEAR)
    }


