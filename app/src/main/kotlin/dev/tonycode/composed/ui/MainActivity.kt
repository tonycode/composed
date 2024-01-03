package dev.tonycode.composed.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import dev.tonycode.composed.common.ui.preview.FontScalePreviews
import dev.tonycode.composed.common.ui.preview.LightDarkPreviews
import dev.tonycode.composed.ui.components.DemoAppCard
import dev.tonycode.composed.ui.theme.ComposedAppTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposedApp()
        }
    }

}


@Composable
private fun ComposedApp() {

    ComposedAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            val context = LocalContext.current

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 32.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(demoApps) { demoApp ->
                    DemoAppCard(demoApp) {
                        demoApp.launcher.invoke(context)
                    }
                }
            }
        }
    }

}

@LightDarkPreviews
@FontScalePreviews
@Composable
private fun PreviewComposedApp() {
    ComposedApp()
}
