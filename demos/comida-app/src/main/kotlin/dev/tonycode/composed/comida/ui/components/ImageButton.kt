package dev.tonycode.composed.comida.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.tonycode.composed.comida.R
import dev.tonycode.composed.comida.ui.preview.ElementPreview
import dev.tonycode.composed.comida.ui.util.shadowCustom


@Composable
fun ImageButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    image: @Composable (() -> Unit),
) {

    Surface(
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
            .size(40.dp)
            .shadowCustom(
                Color(0x55D3D1D8),
                offsetX = 4.dp, offsetY = 12.dp,
                blurRadius = 20.dp
            ),
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(MaterialTheme.shapes.small)
                .clickable { onClick.invoke() },
            contentAlignment = Alignment.Center,
        ) {
            image()
        }
    }

}

@Preview
@Composable
private fun ImageButtonPreview() = ElementPreview(maxWidth = false) {
    ImageButton(onClick = { }) {
        Image(
            painterResource(R.drawable.comida_nav_menu_40),
            contentDescription = "open nav menu",
        )
    }
}
