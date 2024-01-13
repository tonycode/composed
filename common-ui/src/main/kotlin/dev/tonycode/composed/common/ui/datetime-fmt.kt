package dev.tonycode.composed.common.ui

import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


private val defaultDtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
private val defaultTf = DateTimeFormatter.ofPattern("HH:mm")

/**
 * epochMillis -> device local date time
 */
fun Long.fmtAsEpochMillis(
    localZoneId: ZoneId = ZoneId.systemDefault(),
    datetimeFormatter: DateTimeFormatter = defaultDtf,
    timeFormatter: DateTimeFormatter = defaultTf,
): String {
    val instant = Instant.ofEpochMilli(this)
    val ldt = LocalDateTime.ofInstant(instant, localZoneId)

    val todayStart = LocalDateTime.now().with(LocalTime.MIN)
    return if (ldt >= todayStart) {
        "today, " + timeFormatter.format(ldt)
    } else {
        val yesterdayStart = LocalDateTime.now().minusDays(1).with(LocalTime.MIN)
        if (ldt >= yesterdayStart) {
            "yesterday, " + timeFormatter.format(ldt)
        } else {
            datetimeFormatter.format(ldt)
        }
    }
}
