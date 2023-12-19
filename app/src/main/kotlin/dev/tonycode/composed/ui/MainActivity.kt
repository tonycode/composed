package dev.tonycode.composed.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import dev.tonycode.composed.app1.ui.App1Activity
import dev.tonycode.composed.ui.theme.ComposedAppTheme
import dev.tonycode.composed.ui.util.FontScalePreviews
import dev.tonycode.composed.ui.util.LightDarkPreviews


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
            color = MaterialTheme.colorScheme.background
        ) {
            val context = LocalContext.current

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                OutlinedButton(onClick = {
                    App1Activity.launch(context)
                }) { Text("app1") }
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
