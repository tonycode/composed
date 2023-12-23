package dev.tonycode.composed.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.tonycode.composed.R
import dev.tonycode.composed.ui.DemoApp
import dev.tonycode.composed.ui.demoApps
import dev.tonycode.composed.ui.theme.ComposedAppTheme
import dev.tonycode.composed.ui.util.LightDarkPreviews
import dev.tonycode.composed.util.openUrlInExternalApp


@Composable
fun DemoAppCard(
    demoApp: DemoApp,
    onClick: () -> Unit,
) {

    Row(
        modifier = Modifier
            .clip(MaterialTheme.shapes.medium)
            .clickable { onClick.invoke() }
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = demoApp.appName,
                style = MaterialTheme.typography.titleMedium,
            )

            demoApp.designAuthor?.let { designAuthor ->
                Spacer(Modifier.height(6.dp))
                DesignAuthorBlock(name = designAuthor, url = demoApp.designUrl)
            }
        }

        Image(
            painterResource(R.drawable.ic_arrow_forward),
            contentDescription = stringResource(R.string.launch_demo_app),
        )
    }

}

@Composable
private fun DesignAuthorBlock(name: String, url: String?) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "design by",
            style = MaterialTheme.typography.bodyMedium,
        )

        if (url != null) {
            val context = LocalContext.current

            Row(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(color = MaterialTheme.colorScheme.primary),
                    ) {
                        context.openUrlInExternalApp(url)
                    }
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painterResource(R.drawable.logo_dribbble),
                    contentDescription = stringResource(R.string.launch_design_url),
                    modifier = Modifier.size(20.dp),
                )

                Spacer(Modifier.width(6.dp))

                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                )
            }

        } else {  // no url
            Spacer(Modifier.width(6.dp))

            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}


@LightDarkPreviews
@Preview
@Composable
private fun PreviewDemoAppCard() {
    ComposedAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxWidth(),
        ) {
            DemoAppCard(demoApps.first(), { })
            //DemoAppCard(DemoApp("no design url", "design author", null, { }), { })
            //DemoAppCard(DemoApp("no design author-url", null, null, { }), { })
        }
    }
}
