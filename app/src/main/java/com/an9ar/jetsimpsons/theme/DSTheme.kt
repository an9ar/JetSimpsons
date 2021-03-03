package com.an9ar.jetsimpsons.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

@Composable
fun DSTheme(
        darkTheme: Boolean = isSystemInDarkTheme(),
        typography: DSTypography = DSTypography(),
        content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) dsDarkColorPalette() else dsLightColorPalette()
    CompositionLocalProvider(
        LocalDSColor provides colors,
        LocalDSTypography provides typography,
    ) {
        MaterialTheme(
                colors = colors.materialColors,
                typography = typography.materialTypography
        ) {
            content()
        }
    }
}

object DSTheme {

    val colors: DSColorPalette
        @Composable get() = LocalDSColor.current

    val typography: DSTypography
        @Composable get() = LocalDSTypography.current

    val sizes: DSSizes
        @Composable get() = DSSizes()
}

internal val LocalDSColor = compositionLocalOf { dsLightColorPalette() }
internal val LocalDSTypography = compositionLocalOf { DSTypography() }