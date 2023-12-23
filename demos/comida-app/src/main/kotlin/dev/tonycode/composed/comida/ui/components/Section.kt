package dev.tonycode.composed.comida.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.tonycode.composed.comida.R
import dev.tonycode.composed.comida.ui.theme.ComidaAppTheme


@Composable
fun Section(
    title: String,
    modifier: Modifier = Modifier,
    onViewAllClicked: () -> Unit,
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
        )

        Text(
            text = stringResource(R.string.comida_view_all),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .clickable {
                    onViewAllClicked.invoke()
                }
                .padding(horizontal = 4.dp, vertical = 2.dp),
        )
    }

}


@Preview
@Composable
private fun PreviewSection() {
    ComidaAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Section("A Section") { }
        }
    }
}
