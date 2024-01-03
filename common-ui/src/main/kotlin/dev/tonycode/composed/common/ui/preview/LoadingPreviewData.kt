package dev.tonycode.composed.common.ui.preview


/**
 * For usage with [androidx.compose.ui.tooling.preview.PreviewParameterProvider]
 */
sealed class LoadingPreviewData<T> {

    class Data<T>(val data: T) : LoadingPreviewData<T>()

    class Loading<T> : LoadingPreviewData<T>()

}
