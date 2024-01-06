package dev.tonycode.composed.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import dev.tonycode.composed.R
import dev.tonycode.composed.common.ui.preview.LightDarkPreviews
import dev.tonycode.composed.ui.DemoApp
import dev.tonycode.composed.ui.icons.AppIconPack
import dev.tonycode.composed.ui.icons.ArrowRight
import dev.tonycode.composed.ui.preview.ElementPreview
import dev.tonycode.composed.util.openUrlInExternalApp


@Composable
fun DemoAppCard(
    demoApp: DemoApp,
    onClick: () -> Unit,
) {

    val hasUrl = demoApp.designUrl.isNullOrBlank().not()

    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surface,
    ) {
        Row(
            modifier = Modifier.clickable { onClick.invoke() },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // app details
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 0.dp,
                        bottom = if (hasUrl) {
                            10.dp  // for design-author-url button
                        } else {
                            16.dp
                        }
                    ),
            ) {
                Text(
                    text = demoApp.appName,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )

                demoApp.designAuthor?.let { designAuthor ->
                    Spacer(Modifier.height(if (hasUrl) 2.dp else 6.dp))
                    DesignAuthorBlock(name = designAuthor, url = demoApp.designUrl)
                }
            }

            // right arrow
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .size(36.dp)
                    .background(
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = CircleShape,
                    ),
            ) {
                Image(
                    imageVector = AppIconPack.ArrowRight,
                    contentDescription = stringResource(R.string.launch_demo_app),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(start = 2.dp),
                )
            }
        }
    }

}

@Composable
private fun DesignAuthorBlock(name: String, url: String?) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        // "design by" label
        Text(
            stringResource(R.string.design_by),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )

        // design-author info
        if (url != null) {  // clickable design-author
            val context = LocalContext.current

            // button that opens [url]
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
                    painterResource(url.getUrlIconRes()),
                    contentDescription = stringResource(R.string.launch_design_url),
                    modifier = Modifier
                        .size(20.dp)
                        .background(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = CircleShape,
                        ).padding(3.dp),
                )

                Spacer(Modifier.width(4.dp))

                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }

        } else {  // no url - show just design-author name
            Spacer(Modifier.width(6.dp))

            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@DrawableRes
private fun String.getUrlIconRes(): Int = when {
    this.contains("dribbble.com") ->
        R.drawable.logo_dribbble

    this.contains("figma.com") ->
        R.drawable.logo_figma

    else ->
        R.drawable.logo_global
}


@LightDarkPreviews
@Composable
private fun PreviewDemoAppCard(
    @PreviewParameter(DemoAppProvider::class) demoApp: DemoApp,
) = ElementPreview {
    DemoAppCard(demoApp) { /* do nothing onClick */ }
}

private class DemoAppProvider : PreviewParameterProvider<DemoApp> {
    override val values = sequenceOf(
        DemoApp("App with no design-author & -url", null, null) { /* launch nothing */ },
        DemoApp("App with no design-url", "MrCreative", null) { /* launch nothing */ },
        DemoApp("App with dribbble design-url", "MrCreative", "https://dribbble.com/1a2b3c") { /* launch nothing */ },
        DemoApp("App with figma design-url", "MrCreative", "https://figma.com/1a2b3c") { /* launch nothing */ },
        DemoApp("App with unknown design-url", "MrCreative", "https://somesite.com/1a2b3c") { /* launch nothing */ },
    )
}
