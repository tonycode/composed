package dev.tonycode.composed.comida.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.tonycode.composed.comida.R
import dev.tonycode.composed.comida.ui.preview.ElementPreview
import dev.tonycode.composed.comida.ui.theme.ComidaPalette
import dev.tonycode.composed.common.designsystem.ui.poppinsFamily

@Composable
fun BadgeCounter(
    value: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min),
    ) {
        Image(
            painterResource(R.drawable.comida_badge_background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize(),
        )

        Text(
            text = if (value < 99) value.toString() else "99+",
            style =
                TextStyle(
                    fontFamily = poppinsFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 10.sp,
                    platformStyle =
                        PlatformTextStyle(
                            includeFontPadding = false,
                        ),
                ),
            color = ComidaPalette.White,
            modifier =
                Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 2.dp),
        )
    }
}

@Preview
@Composable
private fun BadgeCounterPreview(
    @PreviewParameter(BadgeValuePreviewParameterProvider::class) value: Int,
) = ElementPreview(maxWidth = false) {
    BadgeCounter(value)
}

private class BadgeValuePreviewParameterProvider : PreviewParameterProvider<Int> {
    override val values = sequenceOf(1, 5, 17, 88, 123)
}
