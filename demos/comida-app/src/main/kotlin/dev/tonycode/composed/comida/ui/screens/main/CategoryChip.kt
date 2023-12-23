package dev.tonycode.composed.comida.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.tonycode.composed.comida.data.comidaCategories
import dev.tonycode.composed.comida.model.Category
import dev.tonycode.composed.comida.ui.theme.ComidaAppTheme
import dev.tonycode.composed.comida.ui.util.shadowCustom


@Composable
fun CategoryChip(
    category: Category,
    isChecked: Boolean = false,
    onClicked: () -> Unit,
) {

    val textStyle = MaterialTheme.typography.bodySmall

    Column(
        modifier = Modifier.clickable { onClicked.invoke() },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painterResource(id = category.iconRes),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .shadowCustom(
                    Color(0x3FD3D1D8),
                    offsetX = 0.dp, offsetY = 20.dp,
                    blurRadius = 30.dp
                )
                .clip(CircleShape),
        )

        Spacer(Modifier.height(if (!isChecked) 4.dp else 6.dp))

        Text(
            text = category.title,
            style = if (!isChecked) textStyle else textStyle.copy(fontWeight = FontWeight.SemiBold),
            //color = if (!isChecked) MaterialTheme.colorScheme.onBackground else ComidaPalette.White,
        )
    }

}

@Preview
@Composable
private fun PreviewCategoryChip() {
    ComidaAppTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            val cat1 = comidaCategories.first()
            val cat2 = comidaCategories.last()

            Row {
                CategoryChip(cat1, isChecked = true, onClicked = { })
                Spacer(Modifier.width(8.dp))
                CategoryChip(cat2, isChecked = false, onClicked = { })
            }
        }
    }
}
