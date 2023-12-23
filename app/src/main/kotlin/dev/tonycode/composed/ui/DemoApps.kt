package dev.tonycode.composed.ui

import android.content.Context
import dev.tonycode.composed.comida.ui.ComidaAppActivity


class DemoApp(

    val appName: String,

    val designAuthor: String?,

    val designUrl: String?,

    val launcher: (context: Context) -> Unit,

)


val demoApps = listOf(
    DemoApp(
        appName = "Comida - Food Delivery App",
        designAuthor = "Ola Hamdy",
        designUrl = "https://dribbble.com/shots/23157137-Comida-Food-Delivery-App-UI-Kit",
        launcher = { context ->
            ComidaAppActivity.launch(context)
        }
    ),
)
