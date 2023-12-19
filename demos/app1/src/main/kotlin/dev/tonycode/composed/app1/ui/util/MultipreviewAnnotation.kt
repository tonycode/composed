package dev.tonycode.composed.app1.ui.util

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview


private const val GROUP_UI_MODE = "UI mode"
private const val GROUP_FONT_SCALES = "Font scales"


@Preview(
    name = "a) Light mode",
    group = GROUP_UI_MODE,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true,
)
@Preview(
    name = "b) Dark mode",
    group = GROUP_UI_MODE,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true,
)
annotation class LightDarkPreviews


@Preview(
    name = "a) Large font",
    group = GROUP_FONT_SCALES,
    fontScale = 1.5f,
)
@Preview(
    name = "b) Small font",
    group = GROUP_FONT_SCALES,
    fontScale = 0.5f,
)
annotation class FontScalePreviews
