package dev.tonycode.composed.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import dev.tonycode.composed.ui.theme.Palette


val AppIconPack.ArrowRight: ImageVector
    get() {
        if (_arrowRight != null) {
            return _arrowRight!!
        }

        _arrowRight = Builder(
            name = "ArrowRight",
            defaultWidth = 8.0.dp,
            defaultHeight = 16.0.dp,
            viewportWidth = 8.0f,
            viewportHeight = 16.0f
        ).apply {
            path(
                fill = SolidColor(Color.Transparent),
                stroke = SolidColor(Palette.DiscoBall),
                strokeLineWidth = 2.0f,
                strokeLineCap = Round,
                strokeLineJoin = StrokeJoin.Companion.Round,
                strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(1.0f, 1.0f)
                lineTo(7.0f, 7.5f)
                lineTo(1.0f, 15.0f)
            }
        }.build()

        return _arrowRight!!
    }

private var _arrowRight: ImageVector? = null
