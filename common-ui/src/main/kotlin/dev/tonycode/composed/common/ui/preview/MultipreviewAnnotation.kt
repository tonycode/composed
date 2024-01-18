package dev.tonycode.composed.common.ui.preview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview


private const val GROUP_UI_MODE = "UI mode"
private const val GROUP_FONT_SCALES = "Font scales"


@Preview(
    name = "ui-mode-1 Light",
    group = GROUP_UI_MODE,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true,
)
@Preview(
    name = "ui-mode-2 Dark",
    group = GROUP_UI_MODE,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    showBackground = true,
)
annotation class LightDarkPreviews


@Preview(
    name = "font-scale-1 Large",
    group = GROUP_FONT_SCALES,
    fontScale = 1.5f,
)
@Preview(
    name = "font-scale-2 Small",
    group = GROUP_FONT_SCALES,
    fontScale = 0.5f,
)
annotation class FontScalePreviews
