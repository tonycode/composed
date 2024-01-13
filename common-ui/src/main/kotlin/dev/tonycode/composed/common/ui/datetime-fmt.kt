package dev.tonycode.composed.common.ui

import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


private val defaultDf = DateTimeFormatter.ofPattern("dd-MM-yyyy")
private val defaultTf = DateTimeFormatter.ofPattern("HH:mm")

/**
 * epochMillis -> device local date time
 */
fun Long.fmtAsEpochMillis(
    withTime: Boolean = true,
    localZoneId: ZoneId = ZoneId.systemDefault(),
    dateFormatter: DateTimeFormatter = defaultDf,
    timeFormatter: DateTimeFormatter = defaultTf,
): String {
    val instant = Instant.ofEpochMilli(this)
    val ldt = LocalDateTime.ofInstant(instant, localZoneId)

    val todayStart = LocalDateTime.now().with(LocalTime.MIN)
    val fmtDay = if (ldt >= todayStart) {
        "today"
    } else {
        val yesterdayStart = LocalDateTime.now().minusDays(1).with(LocalTime.MIN)
        if (ldt >= yesterdayStart) {
            "yesterday"
        } else {
            dateFormatter.format(ldt)
        }
    }

    return if (withTime) {
        "$fmtDay, ${ timeFormatter.format(ldt) }"
    } else fmtDay
}
