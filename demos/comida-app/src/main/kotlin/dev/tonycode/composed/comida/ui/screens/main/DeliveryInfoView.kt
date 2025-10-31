package dev.tonycode.composed.comida.ui.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.tonycode.composed.comida.R
import dev.tonycode.composed.comida.ui.preview.ElementPreview

@Composable
fun DeliveryInfoView(
    street: String,
    onSelectorClicked: () -> Unit,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier =
                Modifier
                    .clip(MaterialTheme.shapes.small)
                    .clickable { onSelectorClicked.invoke() }
                    .padding(horizontal = 6.dp, vertical = 2.dp),
        ) {
            Text(
                stringResource(R.string.comida_deliver_to),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.secondary,
            )

            Image(painterResource(R.drawable.comida_ic_arrow_down_20), null)
        }

        Text(
            street,
            style = MaterialTheme.typography.titleSmall,
        )
    }
}

@Preview
@Composable
private fun DeliveryInfoViewPreview() =
    ElementPreview(maxWidth = false) {
        DeliveryInfoView(street = "387 Merdina", onSelectorClicked = { })
    }
