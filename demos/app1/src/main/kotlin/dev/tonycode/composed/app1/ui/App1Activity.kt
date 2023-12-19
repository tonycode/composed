package dev.tonycode.composed.app1.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.tonycode.composed.app1.ui.theme.App1Theme
import dev.tonycode.composed.app1.ui.util.FontScalePreviews
import dev.tonycode.composed.app1.ui.util.LightDarkPreviews


class App1Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposedApp()
        }
    }

    companion object {
        fun launch(context: Context) {
            context.startActivity(
                Intent(context, App1Activity::class.java)
            )
        }
    }

}


@Composable
private fun ComposedApp() {
    App1Theme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Text("App1")
        }
    }
}

@LightDarkPreviews
@FontScalePreviews
@Composable
private fun PreviewComposedApp() {
    ComposedApp()
}
