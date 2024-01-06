package dev.tonycode.composed.mbank.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint


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


val screenHorizontalPadding = 22.dp

@Composable
private fun MbankApp() {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = screenHorizontalPadding),
    ) {
        Text("mBank")
    }
}
