package dev.tonycode.composed.common.datetime.domain

fun Long.secondsToMillis() = (this * 1000)

fun Long.minutesToMillis() = (this * 60).secondsToMillis()

fun Long.hoursToMillis() = (this * 60).minutesToMillis()

// Int compatibility

fun Int.secondsToMillis() = this.toLong().secondsToMillis()

fun Int.minutesToMillis() = this.toLong().minutesToMillis()

fun Int.hoursToMillis() = this.toLong().hoursToMillis()
