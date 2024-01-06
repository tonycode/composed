package dev.tonycode.composed.mbank.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import dev.tonycode.composed.mbank.ui.screens.home.HomeScreen
import dev.tonycode.composed.mbank.ui.theme.MbankAppTheme
import dev.tonycode.composed.mbank.ui.theme.MbankTheme


@AndroidEntryPoint
class MbankAppActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MbankApp()
        }
    }

    companion object {
        fun launch(context: Context) {
            context.startActivity(
                Intent(context, MbankAppActivity::class.java)
            )
        }
    }

}

@Composable
private fun MbankApp() {
    MbankAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MbankTheme.colorScheme.background,
        ) {
            HomeScreen()
        }
    }
}
