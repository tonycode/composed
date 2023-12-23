package dev.tonycode.composed.util

import android.content.Context
import android.content.Intent
import android.net.Uri


/**
 * Open the specified [url] in an external browser (usually the default browser on user device)
 *
 * @return true = browser with the target url was launched,
 *         false = failed to launch the browser (maybe there are no web-browsers on the user device)
 */
fun Context.openUrlInExternalApp(url: String): Boolean {
    var normalizedUrl = url
    if (!url.contains("://")) {
        normalizedUrl = "https://$url"
    }

    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(normalizedUrl))
    return this.safeLaunchIntent(intent)
}

/**
 * Safely launch the target [intent]
 *
 * @return true = success, false = failed to launch the [intent] (usually due to the absence of a target app)
 */
private fun Context.safeLaunchIntent(intent: Intent): Boolean {
    val resolvedActivities = this.packageManager.queryIntentActivities(intent, 0)
    val resolved = resolvedActivities.isNotEmpty()

    if (resolved) {
        this.startActivity(intent)
    }

    return resolved
}
