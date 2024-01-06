package dev.tonycode.composed.mbank.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.tonycode.composed.mbank.ui.theme.MbankTheme


@Composable
fun MbankCard(
    modifier: Modifier = Modifier,
    content: @Composable (() -> Unit),
) {

    Box(
        modifier = modifier
            .background(
                color = MbankTheme.colorScheme.surface,
                shape = RoundedCornerShape(16.dp),
            )
            .padding(14.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        content()
    }

}
