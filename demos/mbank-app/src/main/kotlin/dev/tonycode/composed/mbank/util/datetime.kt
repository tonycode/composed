package dev.tonycode.composed.mbank.util


fun Long.secondsToMillis() = (this * 1000)

fun Long.minutesToMillis() = (this * 60).secondsToMillis()

fun Long.hoursToMillis() = (this * 60).minutesToMillis()
