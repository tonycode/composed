package dev.tonycode.composed.mbank.presentation.formatter

import dev.tonycode.composed.common.datetime.presentation.fmtAsEpochMillis
import dev.tonycode.composed.mbank.domain.entity.Transaction

val Transaction.performedDay: String
    get() = performedAt.fmtAsEpochMillis(withTime = false)
