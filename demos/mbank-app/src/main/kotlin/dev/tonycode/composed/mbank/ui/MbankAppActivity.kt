package dev.tonycode.composed.mbank.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import dev.tonycode.composed.common.ui.preview.LightDarkPreviews
import dev.tonycode.composed.mbank.ui.components.MbankBottomBar
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
        var selectedScreen: Screen by remember { mutableStateOf(Screen.Home) }

        Scaffold(
            containerColor = MbankTheme.colorScheme.background,
            bottomBar = {
                MbankBottomBar(
                    items = screens,
                    selectedItem = selectedScreen,
                    onItemSelected = {
                        selectedScreen = it
                    })
            }
        ) { innerPadding ->
            HomeScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}

@LightDarkPreviews
@Composable
private fun MbankAppPreview() = MbankApp()
