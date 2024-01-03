package dev.tonycode.composed.comida.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.tonycode.composed.comida.ui.components.BottomNav
import dev.tonycode.composed.comida.ui.screens.main.MainScreen
import dev.tonycode.composed.comida.ui.theme.ComidaAppTheme
import org.koin.compose.KoinApplication


class ComidaAppActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComidaApp()
        }
    }

    companion object {
        fun launch(context: Context) {
            context.startActivity(
                Intent(context, ComidaAppActivity::class.java)
            )
        }
    }

}


val screenHorizontalPadding = 22.dp

@Composable
private fun ComidaApp() {
    KoinApplication(application = {
        modules(comidaAppModule)
    }) {
        ComidaAppTheme {
            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.fillMaxSize(),
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    MainScreen()

                    BottomNav(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 18.dp, end = 18.dp, bottom = 18.dp)
                            .align(Alignment.BottomCenter),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewComposedApp() {
    ComidaApp()
}
