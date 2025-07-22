package com.mariam.android.challenge.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import com.mariam.android.challenge.core.ui.theme.colors.ColorPalette
import com.mariam.android.challenge.core.ui.theme.colors.darkColor
import com.mariam.android.challenge.core.ui.theme.colors.lightColors

@Composable
fun ProvideAndroidCodeChallengeTheme(
    content: @Composable () -> Unit
) {
    val colors = if (isSystemInDarkTheme()) darkColor else lightColors
    val typography = AppTypography()

    CompositionLocalProvider(
        LocalColors provides colors,
        LocalTypography provides typography,
        content = content
    )
}

object AndroidCodeChallengeTheme {
    val colors: ColorPalette
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

internal val LocalColors = staticCompositionLocalOf { ColorPalette() }
internal val LocalTypography = staticCompositionLocalOf { AppTypography() }
