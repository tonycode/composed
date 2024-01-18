package dev.tonycode.composed.comida.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import dev.tonycode.composed.comida.data.dummy.comidaCategories
import dev.tonycode.composed.comida.model.Category
import dev.tonycode.composed.comida.ui.preview.ElementPreview
import dev.tonycode.composed.comida.ui.theme.ComidaPalette
import dev.tonycode.composed.comida.ui.util.shadowCustom


@Composable
fun CategoryChip(
    category: Category,
    isSelected: Boolean = false,
    onClicked: () -> Unit,
) {

    val textStyle = MaterialTheme.typography.bodySmall

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(100))
            .clickable { onClicked.invoke() }
            .background(if (isSelected) Color(0xFF6ED39D) else Color.Transparent)
            .then(
                if (isSelected) Modifier.padding(start = 6.dp, top = 4.dp, end = 6.dp, bottom = 14.dp)
                else Modifier.padding(start = 8.dp, top = 12.dp, end = 8.dp, bottom = 12.dp)
            )
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painterResource(id = category.iconRes),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .shadowCustom(
                    Color(0x3FD3D1D8),
                    offsetX = 0.dp, offsetY = 8.dp,
                    blurRadius = 12.dp
                )
                .clip(CircleShape)
                .background(color = Color.White),
        )

        Spacer(Modifier.height(if (!isSelected) 4.dp else 8.dp))

        Text(
            text = category.title,
            style = if (!isSelected) textStyle else textStyle.copy(fontWeight = FontWeight.SemiBold),
            color = if (!isSelected) MaterialTheme.colorScheme.onBackground else ComidaPalette.White,
        )
    }

}


@Preview
@Composable
private fun CategoryChipPreview(
    @PreviewParameter(CategoryChipPreviewStateProvider::class) previewState: CategoryChipPreviewState,
) = ElementPreview(maxWidth = false) {
    CategoryChip(
        category = previewState.category,
        isSelected = previewState.isSelected,
        onClicked = { }
    )
}

private class CategoryChipPreviewState(
    val category: Category,
    val isSelected: Boolean,
)

private class CategoryChipPreviewStateProvider : PreviewParameterProvider<CategoryChipPreviewState> {
    override val values = sequenceOf(
        CategoryChipPreviewState(comidaCategories.first(), false),
        CategoryChipPreviewState(comidaCategories.last(), true),
    )
}
