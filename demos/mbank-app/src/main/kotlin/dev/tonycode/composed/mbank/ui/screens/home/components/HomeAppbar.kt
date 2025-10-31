package dev.tonycode.composed.mbank.ui.screens.home.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer
import dev.tonycode.composed.common.designsystem.ui.preview.LightDarkPreviews
import dev.tonycode.composed.common.designsystem.ui.skeleton.Skeleton
import dev.tonycode.composed.mbank.R
import dev.tonycode.composed.mbank.data.stubUserProfile
import dev.tonycode.composed.mbank.ui.preview.ElementPreview
import dev.tonycode.composed.mbank.ui.theme.MbankTheme


private val iconContentSize = 24.dp
private val iconTouchMargin = 6.dp

private val appbarPaddingTop = 14.dp
private val appbarPaddingBottom = 18.dp
private val appbarHeight = iconContentSize + appbarPaddingTop + appbarPaddingBottom

@Composable
fun HomeAppbar(
    isLoading: Boolean = true,
    userName: String? = null,
    onAccountDetailsClicked: (() -> Unit)? = null,
    onSettingsScreenClicked: (() -> Unit)? = null,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(appbarHeight),
        color = Color.Unspecified,
    ) {
        Crossfade(
            targetState = isLoading,
            label = "Greeting crossfade",
        ) { isLoading ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 14.dp,
                        top = appbarPaddingTop - iconTouchMargin,
                        end = 14.dp,
                        bottom = appbarPaddingBottom - iconTouchMargin
                    ),
            ) {
                if (isLoading) {
                    Skeleton(
                        width = 224.dp,
                        height = MbankTheme.typography.appbarTitle.lineHeight.value.dp,
                        modifier = Modifier.shimmer(),
                    )

                } else {  // data for HomeAppbar was loaded
                    requireNotNull(userName)

                    Text(
                        stringResource(R.string.mbank_greeting_pattern, userName),
                        style = MbankTheme.typography.appbarTitle,
                        color = MbankTheme.colorScheme.onBackground,
                        modifier = Modifier.weight(1f),
                    )

                    Image(
                        painterResource(R.drawable.mbank_ic_account_24),
                        contentDescription = stringResource(R.string.mbank_open_account_details),
                        colorFilter = ColorFilter.tint(MbankTheme.colorScheme.onBackground),
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable { onAccountDetailsClicked?.invoke() }
                            .size(24.dp + iconTouchMargin * 2)
                            .padding(iconTouchMargin),
                    )

                    Image(
                        painterResource(R.drawable.mbank_ic_settings_24),
                        contentDescription = stringResource(R.string.mbank_open_setting_screen),
                        colorFilter = ColorFilter.tint(MbankTheme.colorScheme.onBackground),
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable { onSettingsScreenClicked?.invoke() }
                            .size(36.dp)
                            .padding(6.dp),
                    )
                }
            }
        }
    }
}


@LightDarkPreviews
@Composable
private fun HomeAppbarPreview(
    @PreviewParameter(HomeAppbarPreviewStateProvider::class) previewState: HomeAppbarPreviewState,
) = ElementPreview(usePadding = false) {
    HomeAppbar(
        isLoading = previewState.isLoading,
        userName = previewState.userName,
    )
}

private class HomeAppbarPreviewState(
    val isLoading: Boolean = true,
    val userName: String? = null,
)

private class HomeAppbarPreviewStateProvider : PreviewParameterProvider<HomeAppbarPreviewState> {
    override val values = sequenceOf(
        HomeAppbarPreviewState(isLoading = true),
        HomeAppbarPreviewState(isLoading = false, userName = stubUserProfile.name),
    )
}
