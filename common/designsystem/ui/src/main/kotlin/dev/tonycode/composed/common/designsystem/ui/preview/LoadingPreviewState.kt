package dev.tonycode.composed.common.designsystem.ui.preview


/**
 * For usage with [androidx.compose.ui.tooling.preview.PreviewParameterProvider]
 */
sealed class LoadingPreviewState<T> {

    class Data<T>(val data: T) : LoadingPreviewState<T>()

    class Loading<T> : LoadingPreviewState<T>()

}
