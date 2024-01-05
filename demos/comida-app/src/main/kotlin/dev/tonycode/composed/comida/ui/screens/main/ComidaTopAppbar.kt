package dev.tonycode.composed.comida.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer
import dev.tonycode.composed.comida.R
import dev.tonycode.composed.comida.ui.components.ImageButton
import dev.tonycode.composed.comida.ui.components.preview.Skeleton
import dev.tonycode.composed.comida.ui.preview.ElementPreview
import dev.tonycode.composed.comida.ui.screenHorizontalPadding


@Composable
fun ComidaTopAppbar(
    modifier: Modifier = Modifier,
    onNavMenuClicked: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ImageButton(onClick = { onNavMenuClicked.invoke() }) {
            Image(
                painterResource(R.drawable.nav_menu_40),
                contentDescription = "open nav menu",
            )
        }

        DeliveryInfoView(
            street = "387 Merdina",
            onSelectorClicked = { },
        )

        // Avatar
        Skeleton(
            width = 40.dp,
            height = 40.dp,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.shimmer(),
        )
    }
}

@Preview
@Composable
private fun PreviewComidaTopAppbar() = ElementPreview {
    ComidaTopAppbar(
        modifier = Modifier.padding(horizontal = screenHorizontalPadding),
        onNavMenuClicked = { },
    )
}
