package dev.tonycode.composed.common.ui

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.time.ZoneId


class DatetimeFmtUnitTest {

    @Test
    fun testFmtAsEpochMillis() {
        val utcZoneId = ZoneId.of("UTC")

        assertThat(
            0L.fmtAsEpochMillis(utcZoneId)
        ).isEqualTo("01-01-1970 00:00")

        assertThat(
            System.currentTimeMillis().fmtAsEpochMillis(utcZoneId)
        ).startsWith("today, ")

        assertThat(
            (System.currentTimeMillis() - 24.hoursToMillis()).fmtAsEpochMillis(utcZoneId)
        ).startsWith("yesterday, ")
    }

}
