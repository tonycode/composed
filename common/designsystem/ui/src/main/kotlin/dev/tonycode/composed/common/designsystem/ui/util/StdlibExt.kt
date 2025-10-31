package dev.tonycode.composed.common.designsystem.ui.util

/**
 * This is really just `?.let` but follows the Kotlin way by being more explicit
 *
 * credits: https://mastodon.social/@dkandalov/111097207885469957
 */
inline fun <T : Any, R> T?.ifNotNull(f: (T) -> R): R? = this?.let(f)
