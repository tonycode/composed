package dev.tonycode.composed.common.designsystem.ui.modifier

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@SuppressLint("ComposableModifierFactory")
@Composable
fun Modifier.thenIf(
    predicate: Boolean,
    function: @Composable (Modifier.() -> Modifier),
): Modifier =
    if (predicate) {
        this.then(function())
    } else {
        this
    }
