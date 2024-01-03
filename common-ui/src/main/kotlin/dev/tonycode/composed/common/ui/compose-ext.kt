package dev.tonycode.composed.common.ui

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@SuppressLint("ComposableModifierFactory")
@Composable
fun Modifier.thenIf(
    predicate: Boolean,
    function: @Composable (Modifier.() -> Modifier),
): Modifier {
    return if (predicate) {
        function.invoke(this)
    } else {
        this
    }
}
