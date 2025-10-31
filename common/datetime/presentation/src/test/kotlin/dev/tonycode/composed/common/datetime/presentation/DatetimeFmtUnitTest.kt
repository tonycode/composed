package dev.tonycode.composed.common.datetime.presentation

import com.google.common.truth.Truth.assertThat
import dev.tonycode.composed.common.datetime.domain.hoursToMillis
import org.junit.Test
import java.time.ZoneId

class DatetimeFmtUnitTest {
    @Test
    fun `test fmtAsEpochMillis() ext`() {
        val utcZoneId = ZoneId.of("UTC")

        // zero
        assertThat(
            0L.fmtAsEpochMillis(localZoneId = utcZoneId),
        ).isEqualTo("01-01-1970, 00:00")

        assertThat(
            0L.fmtAsEpochMillis(withTime = false, localZoneId = utcZoneId),
        ).isEqualTo("01-01-1970")

        // today
        assertThat(
            System.currentTimeMillis().fmtAsEpochMillis(localZoneId = utcZoneId),
        ).startsWith("today, ")

        assertThat(
            System.currentTimeMillis().fmtAsEpochMillis(withTime = false, localZoneId = utcZoneId),
        ).isEqualTo("today")

        // yesterday
        assertThat(
            (System.currentTimeMillis() - 24.hoursToMillis()).fmtAsEpochMillis(localZoneId = utcZoneId),
        ).startsWith("yesterday, ")

        assertThat(
            (System.currentTimeMillis() - 24.hoursToMillis())
                .fmtAsEpochMillis(withTime = false, localZoneId = utcZoneId),
        ).isEqualTo("yesterday")
    }
}
